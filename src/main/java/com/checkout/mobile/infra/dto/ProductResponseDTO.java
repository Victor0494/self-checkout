package com.checkout.mobile.infra.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(String id, String name, BigDecimal price, String barCode, String description, String imgPath) {
}
