package com.checkout.mobile.application.gateways;

import com.checkout.mobile.domain.entities.Client;

public interface ClientGateway {

    Client createClient(Client client);
}
