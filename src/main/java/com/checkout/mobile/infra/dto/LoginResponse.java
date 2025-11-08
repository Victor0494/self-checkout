package com.checkout.mobile.infra.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
