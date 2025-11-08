package com.checkout.mobile.infra.dto;

import java.math.BigDecimal;

public record PopularProductsDTO(String name, BigDecimal price, String imgPath) {
}
