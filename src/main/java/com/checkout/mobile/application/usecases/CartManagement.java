package com.checkout.mobile.application.usecases;

import com.checkout.mobile.application.gateways.CartGateway;
import com.checkout.mobile.domain.entities.Cart;
import com.checkout.mobile.domain.valueObject.Item;
import org.json.JSONObject;

public class CartManagement {

    private final CartGateway cartGateway;

    public CartManagement(CartGateway cartGateway) {
        this.cartGateway = cartGateway;
    }

    public void addItemToCart(String productId, String name, Integer quantity, Float price, String imgPath) {
        cartGateway.addItem(productId, name, quantity, price, imgPath);
    }

    public void removeItem(String productId) {
        cartGateway.removerItem(productId);
    }

    public void clearCart() {
        cartGateway.cleanCart();
    }

    public Cart getUserCartInfo() {
        return cartGateway.getUserCartInfo();
    }

    public String checkout() {
        return cartGateway.checkout();
    }
}
