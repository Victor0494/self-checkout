package com.checkout.mobile.domain.valueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public enum CartStatus {

    OPEN("Aberto"),
    WAITING_PAYMENT("Aguardando pagamento"),
    CHECKED_OUT("Finalizado"),
    CANCELLED("Cancelado");

    private final String description;

    CartStatus(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
