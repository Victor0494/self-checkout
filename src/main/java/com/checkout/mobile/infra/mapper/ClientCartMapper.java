package com.checkout.mobile.infra.mapper;

import com.checkout.mobile.domain.entities.Client;
import com.checkout.mobile.infra.persistence.client.CartEntity;
import com.checkout.mobile.infra.persistence.client.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientCartMapper {

    private final CartEntityMapper cartEntityMapper;
    private final ItemEntityMapper itemEntityMapper;

    public ClientCartMapper(CartEntityMapper cartEntityMapper, ItemEntityMapper itemEntityMapper) {
        this.cartEntityMapper = cartEntityMapper;
        this.itemEntityMapper = itemEntityMapper;
    }

    public Client toDomain(ClientEntity entity) {
        Client client = new Client();
        client.setId(entity.getId());
        client.setName(entity.getName());
        client.setDocument(entity.getDocument());
        client.setCart(cartEntityMapper.toDomain(entity.getCart()));
        return client;
    }

    public ClientEntity toEntity(Client client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());
        clientEntity.setName(client.getName());
        clientEntity.setDocument(client.getDocument());

        List<CartEntity> cartEntities = cartEntityMapper.toEntity(client.getCart());
        cartEntities.forEach(cart -> cart.setClient(clientEntity));

        clientEntity.setCart(cartEntities);
        return clientEntity;
    }
}
