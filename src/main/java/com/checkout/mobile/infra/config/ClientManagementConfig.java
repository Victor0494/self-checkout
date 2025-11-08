package com.checkout.mobile.infra.config;

import com.checkout.mobile.application.gateways.ClientGateway;
import com.checkout.mobile.application.usecases.ClientManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientManagementConfig {

    @Bean
    ClientManagement createClientManagement(ClientGateway clientGateway) {
        return new ClientManagement(clientGateway);
    }
}
