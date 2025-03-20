package com.sheffieldcorwin.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.sheffieldcorwin.model.SandwichShop;

public interface SandwichShopRepository extends ListCrudRepository<SandwichShop, Long> {


}
