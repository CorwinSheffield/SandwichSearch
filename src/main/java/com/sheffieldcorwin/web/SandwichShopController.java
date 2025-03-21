package com.sheffieldcorwin.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sheffieldcorwin.model.Rating;
import com.sheffieldcorwin.model.SandwichShop;
import com.sheffieldcorwin.model.SandwichShopWithRatings;
import com.sheffieldcorwin.service.SandwichShopService;

@RestController
@CrossOrigin
public class SandwichShopController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SandwichShopService sandwichShopService;

	@GetMapping("/sandwichshops")
	public List<SandwichShop> getAllSandwichShops() {
		return sandwichShopService.getAllSandwichShops();
	}

	@GetMapping("/sandwichshops/{id}")
	public ResponseEntity<SandwichShop> getById(@PathVariable Long id) {
		Optional<SandwichShop> shop = sandwichShopService.getSandwichShopById(id);
		return shop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/sandwichshops")
	public ResponseEntity<SandwichShop> createSandwichShop(@RequestBody SandwichShop sandwichShop) {
		SandwichShop shop = sandwichShopService.createSandwichShop(sandwichShop);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shop.id()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/sandwichshops/{id}/ratings")
	public List<Rating> getRatingsForShop(@PathVariable Long id){
		return sandwichShopService.getRatingsForShop(id);
	}
	
	
	@GetMapping("/sandwichshops/ratings")
	public List<SandwichShopWithRatings> getRatingsForAllShops(){
		return sandwichShopService.getAllShopsWithRatings();
	}
}
