package com.sheffieldcorwin.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sheffieldcorwin.dao.SandwichShopRepository;
import com.sheffieldcorwin.model.SandwichShop;

@RestController
public class SandwichShopController {
	
	
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SandwichShopRepository sandwichShopRepository;


	@GetMapping("/sandwichshops")
	public List<SandwichShop> getAllRestaurants() {
		return sandwichShopRepository.findAll();
	}
	
	
	@GetMapping("/sandwichshops/{id}")
	public SandwichShop getById(@PathVariable Long id) {
		Optional<SandwichShop> shop = sandwichShopRepository.findById(id);
		return shop.get();
	}

	@PostMapping("/sandwichshops")
	public ResponseEntity<Void> addRestaurant(@RequestBody SandwichShop sandwichShop) {
		logger.debug("Entering Post Mapping. SandwichShop requested: {}", sandwichShop.toString());
		SandwichShop newShop = sandwichShopRepository.save(sandwichShop);
		
		URI location = URI.create("/sandwichshops/"+  newShop.id());
		
		return ResponseEntity.created(location)
				.build();

	}

}
