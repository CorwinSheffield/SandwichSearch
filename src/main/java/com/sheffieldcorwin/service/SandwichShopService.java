package com.sheffieldcorwin.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sheffieldcorwin.dao.RatingsRepository;
import com.sheffieldcorwin.dao.SandwichShopRepository;
import com.sheffieldcorwin.model.Rating;
import com.sheffieldcorwin.model.SandwichShop;
import com.sheffieldcorwin.model.SandwichShopWithRatings;

@Service
public class SandwichShopService {

	private final SandwichShopRepository sandwichShopRepository;
	private final RatingsRepository ratingsRepository;

	SandwichShopService(SandwichShopRepository sandwichShopRepository, RatingsRepository ratingsRepository) {
		this.sandwichShopRepository = sandwichShopRepository;
		this.ratingsRepository = ratingsRepository;
	}

	public List<SandwichShop> getAllSandwichShops() {
		return sandwichShopRepository.findAll();
	}

	public Optional<SandwichShop> getSandwichShopById(Long id) {
		return sandwichShopRepository.findById(id);
	}

	public SandwichShop createSandwichShop(SandwichShop sandwichShop) {
		return sandwichShopRepository.save(sandwichShop);
	}

	public List<Rating> getRatingsForShop(Long shopId) {
		return ratingsRepository.findBySandwichshopId(shopId);
	}

	public Rating createRating(Rating rating) {
		Rating savedRating = ratingsRepository.save(rating);
		updateSandwichShopAverageRating(savedRating.sandwichshopId());
		return savedRating;
	}
	
	public List<SandwichShopWithRatings> getAllShopsWithRatings(){
		List<SandwichShop> shops = sandwichShopRepository.findAll();
		List<Rating> ratings = ratingsRepository.findAll();
		
		Map<Long, List<Rating>> ratingsById = ratings.stream().collect(Collectors.groupingBy(Rating::sandwichshopId));
		return shops.stream().map(shop ->{ 
			List<Rating> shopRatings = ratingsById.getOrDefault(shop.id(), new ArrayList<>());
			return new SandwichShopWithRatings(shop.id(), shop.name(),shop.rating(), shopRatings);
		}).collect(Collectors.toList());
	}

	private void updateSandwichShopAverageRating(Long sandwichshopId) {
		Optional<SandwichShop> shopOptional = sandwichShopRepository.findById(sandwichshopId);
		if (shopOptional.isPresent()) {
			SandwichShop shop = shopOptional.get();
			List<Rating> ratings = ratingsRepository.findBySandwichshopId(sandwichshopId);
			BigDecimal averageRating = calculateAverageRating(ratings);
			sandwichShopRepository.save(new SandwichShop(shop.id(), shop.name(), averageRating));
		}
	}

	private BigDecimal calculateAverageRating(List<Rating> ratings) {
		if (ratings.isEmpty()) {
			return BigDecimal.ZERO;
		}

		BigDecimal sum = BigDecimal.ZERO;
		for (Rating rating : ratings) {
			sum = sum.add(rating.rating());
		}
		return sum.divide(BigDecimal.valueOf(ratings.size()), 2, RoundingMode.HALF_UP);
	}
}
