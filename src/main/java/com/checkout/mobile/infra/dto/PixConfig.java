package com.checkout.mobile.infra.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.pix")
public record PixConfig(String clientId, String clientSecret, boolean sandbox, boolean debug, String certificatePath) {
}
