package com.checkout.mobile.infra.config;

import com.checkout.mobile.application.gateways.LoginGateway;
import com.checkout.mobile.application.usecases.LoginManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginManagementConfig {

    @Bean
    public LoginManagement getLoginManagement(LoginGateway loginGateway) {
        return new LoginManagement(loginGateway);
    }

}
