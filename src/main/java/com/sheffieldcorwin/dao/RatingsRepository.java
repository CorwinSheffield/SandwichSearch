package com.sheffieldcorwin.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.sheffieldcorwin.model.Rating;

public interface RatingsRepository extends ListCrudRepository<Rating, Long> {

	List<Rating> findBySandwichshopId(Long shopId);

}
