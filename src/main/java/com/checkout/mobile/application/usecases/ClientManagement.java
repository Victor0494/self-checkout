package com.checkout.mobile.application.usecases;

import com.checkout.mobile.application.gateways.ClientGateway;
import com.checkout.mobile.domain.entities.Client;

public class ClientManagement {

    private final ClientGateway clientGateway;

    public ClientManagement(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    public Client createClient(Client client) {
        return clientGateway.createClient(client);
    }
}
