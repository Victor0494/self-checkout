package com.checkout.mobile.infra.config;

import com.checkout.mobile.application.gateways.ProductGateway;
import com.checkout.mobile.application.usecases.ProductManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductManagementConfig {

    @Bean
    public ProductManagement getProductManagement(ProductGateway productGateway) {
        return new ProductManagement(productGateway);
    }
}
