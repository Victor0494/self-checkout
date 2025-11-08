package com.checkout.mobile.infra.gateways;

import com.checkout.mobile.application.gateways.ClientGateway;
import com.checkout.mobile.domain.entities.Client;
import com.checkout.mobile.domain.valueObject.CartStatus;
import com.checkout.mobile.infra.persistence.client.CartEntity;
import com.checkout.mobile.infra.persistence.client.ClientEntity;
import com.checkout.mobile.infra.persistence.client.ClientRepository;
import com.checkout.mobile.infra.persistence.userLogin.UserLoginEntity;
import com.checkout.mobile.infra.persistence.userLogin.UserLoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientManagementImpl implements ClientGateway {

    private final UserLoginRepository userLoginRepository;

    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    public ClientManagementImpl(UserLoginRepository userLoginRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.userLoginRepository = userLoginRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client createClient(Client client) {
        UserLoginEntity userLogin = userLoginRepository
                .save(new UserLoginEntity(client.getDocument(),
                        passwordEncoder.encode(client.getPassword())));

        ClientEntity clientEntity = ClientEntity.builder()
                .id(userLogin.getId())
                .name(client.getName())
                .document(client.getDocument())
                .build();

        CartEntity cart = CartEntity.builder()
                .items(List.of())
                .totalValue(0.0f)
                .status(CartStatus.OPEN)
                .client(clientEntity)
                .build();

        clientEntity.addCart(cart);

        ClientEntity response = clientRepository.save(clientEntity);

        return new Client(response.getId(), response.getName());
    }
}
