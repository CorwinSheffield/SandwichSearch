package com.sheffieldcorwin.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("SANDWICHSHOP")
public record SandwichShop(@Id Long id, String name, BigDecimal rating) {

}
