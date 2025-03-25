package com.sheffieldcorwin.dao;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.sheffieldcorwin.model.SandwichShop;

public interface SandwichShopRepository extends ListCrudRepository<SandwichShop, Long> {
	public Optional<SandwichShop> findByNameIgnoreCase(String name);

}
