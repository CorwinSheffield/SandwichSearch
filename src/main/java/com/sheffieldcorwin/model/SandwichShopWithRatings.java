package com.sheffieldcorwin.model;

import java.math.BigDecimal;
import java.util.List;

public record SandwichShopWithRatings(Long id, String name, BigDecimal rating, List<Rating> ratings) {

}
