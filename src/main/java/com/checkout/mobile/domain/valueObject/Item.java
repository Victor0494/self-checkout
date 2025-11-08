package com.checkout.mobile.domain.valueObject;

import com.checkout.mobile.domain.entities.Cart;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {

    private String id;
    private String productId;
    private String name;
    private Integer quantity;
    private Float pricePerUnity;
    private String imgPath;
    private Cart cart;

    public Item(String id, String productId, String name, Integer quantity, Float price) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnity = price;
    }

    public Item(String productId, String name, Integer quantity, Float price, String imgPath) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnity = price;
        this.imgPath = imgPath;
    }

    public Item() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(Integer quantity) {
        this.setQuantity(this.getQuantity() + quantity);
    }

    public Float getPricePerUnity() {
        return pricePerUnity;
    }

    public void setPricePerUnity(Float pricePerUnity) {
        this.pricePerUnity = pricePerUnity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}
