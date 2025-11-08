package com.checkout.mobile.application.usecases;

import com.checkout.mobile.application.gateways.ProductGateway;
import com.checkout.mobile.domain.entities.Product;

import java.util.List;

public class ProductManagement {

    private final ProductGateway productGateway;

    public ProductManagement(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product getProductByBarCode(String barCode){
        return productGateway.findByBarCode(barCode);
    }

    public List<Product> getAllProduct() {
        return productGateway.getAll();
    }
}
