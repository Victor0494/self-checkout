package com.checkout.mobile.application.gateways;

import com.checkout.mobile.domain.entities.Cart;
import com.checkout.mobile.domain.valueObject.Item;
import org.json.JSONObject;

public interface CartGateway {

    void addItem(String productId, String name, Integer quantity, Float price, String imgPath);

    void removerItem(String productId);

    void cleanCart();

    Cart getUserCartInfo();

    String checkout();

}
