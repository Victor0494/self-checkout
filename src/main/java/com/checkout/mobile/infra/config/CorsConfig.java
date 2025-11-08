package com.checkout.mobile.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "http://localhost:4200",
                        "https://*.ngrok-free.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);

        registry.addMapping("/images/**")
                .allowedOriginPatterns(
                        "http://localhost:4200",
                        "https://*.ngrok-free.app"
                )
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(true);
}

}
