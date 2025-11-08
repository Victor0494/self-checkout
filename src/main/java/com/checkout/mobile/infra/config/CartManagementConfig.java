package com.checkout.mobile.infra.config;

import com.checkout.mobile.application.gateways.CartGateway;
import com.checkout.mobile.application.usecases.CartManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartManagementConfig {

    @Bean
    CartManagement cartManagement(CartGateway cartGateway) {
        return new CartManagement(cartGateway);
    }
}
