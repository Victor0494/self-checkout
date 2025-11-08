package com.checkout.mobile.infra.dto;

import com.checkout.mobile.domain.valueObject.CartStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CartDTO(String id, List<ItemDto> items, CartStatus status) {

    @JsonProperty("totalValue")
    public Float totalValue() {
        return items.stream()
                .map(ItemDto::totalValue)
                .reduce(0f, Float::sum);
    }
}
