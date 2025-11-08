package com.checkout.mobile.application.gateways;

import com.checkout.mobile.domain.entities.Product;

import java.util.List;

public interface ProductGateway {

    Product findByBarCode(String barCode);

    List<Product> getAll();
}
