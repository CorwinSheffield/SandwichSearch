package com.sheffieldcorwin.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("RATINGS")
public record Rating(@Id Long id,  Long sandwichshopId, BigDecimal rating, String review) {

}
