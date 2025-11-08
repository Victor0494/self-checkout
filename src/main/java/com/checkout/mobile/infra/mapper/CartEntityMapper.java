package com.checkout.mobile.infra.mapper;

import com.checkout.mobile.domain.entities.Cart;
import com.checkout.mobile.domain.entities.Client;
import com.checkout.mobile.domain.valueObject.Item;
import com.checkout.mobile.infra.persistence.client.CartEntity;
import com.checkout.mobile.infra.persistence.client.ClientEntity;
import com.checkout.mobile.infra.persistence.client.ItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartEntityMapper {

    private final ItemEntityMapper itemEntityMapper;

    public CartEntityMapper(ItemEntityMapper itemEntityMapper) {
        this.itemEntityMapper = itemEntityMapper;
    }

    public List<Cart> toDomain(List<CartEntity> cartEntities) {
        return cartEntities.stream()
                .map(cartEntity -> {
                    Cart cart = new Cart();
                    cart.setId(cartEntity.getId());
                    cart.setTotalValue(cartEntity.getTotalValue());
                    cart.setStatus(cartEntity.getStatus());

                    List<Item> items = cartEntity.getItems().stream()
                            .map(itemEntity -> itemEntityMapper.toDomain(itemEntity, cart))
                            .collect(Collectors.toList());

                    cart.setItems(items);
                    cart.setClient(new Client());
                    return cart;
                })
                .collect(Collectors.toList());
    }

    public List<CartEntity> toEntity(List<Cart> carts) {
        return carts.stream()
                .map(cart -> {
                    CartEntity cartEntity = new CartEntity();
                    if (cart.getId() != null && !cart.getId().isBlank()) {
                        cartEntity.setId(cart.getId());
                    }
                    cartEntity.setTotalValue(cart.getTotalValue());
                    cartEntity.setStatus(cart.getStatus());
                    cartEntity.setClient(null);

                    List<ItemEntity> items = cart.getItems().stream()
                            .map(item -> itemEntityMapper.toEntity(item, cartEntity))
                            .collect(Collectors.toList());

                    cartEntity.setItems(items);
                    return cartEntity;
                })
                .collect(Collectors.toList());
    }


}
