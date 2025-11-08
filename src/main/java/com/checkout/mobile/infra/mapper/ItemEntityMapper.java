package com.checkout.mobile.infra.mapper;

import com.checkout.mobile.domain.entities.Cart;
import com.checkout.mobile.domain.valueObject.Item;
import com.checkout.mobile.infra.persistence.client.CartEntity;
import com.checkout.mobile.infra.persistence.client.ItemEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ItemEntityMapper {

    public Item toDomain(ItemEntity itemEntity, Cart parentCart) {
        Item item = new Item();
        item.setId(itemEntity.getId());
        item.setName(itemEntity.getName());
        item.setProductId(itemEntity.getProductId());
        item.setQuantity(itemEntity.getQuantity());
        item.setPricePerUnity(itemEntity.getPricePerUnity());
        item.setCart(parentCart);
        item.setImgPath(itemEntity.getImgPath());
        return item;
    }

    public Item toDomainWithoutCart(ItemEntity itemEntity) {
        Item item = new Item();
        item.setId(itemEntity.getId());
        item.setName(itemEntity.getName());
        item.setProductId(itemEntity.getProductId());
        item.setQuantity(itemEntity.getQuantity());
        item.setPricePerUnity(itemEntity.getPricePerUnity());
        item.setImgPath(itemEntity.getImgPath());
        return item;
    }

    public ItemEntity toEntity(Item item, CartEntity cartEntity) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setProductId(item.getProductId());
        itemEntity.setQuantity(item.getQuantity());
        itemEntity.setPricePerUnity(item.getPricePerUnity());
        itemEntity.setCart(cartEntity);
        itemEntity.setImgPath(item.getImgPath());
        return itemEntity;
    }

}
