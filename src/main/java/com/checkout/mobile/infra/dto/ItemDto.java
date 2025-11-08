package com.checkout.mobile.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ItemDto(String productId, String name, Integer quantity, Float pricePerUnity, String imgPath) {

    @JsonProperty("totalValue")
    public Float totalValue() {
        return quantity * pricePerUnity;
    }
}
