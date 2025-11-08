package com.checkout.mobile.infra.gateways;

import com.checkout.mobile.application.gateways.ProductGateway;
import com.checkout.mobile.domain.entities.Product;
import com.checkout.mobile.infra.dto.PopularProductsDTO;
import com.checkout.mobile.infra.persistence.product.ProductEntity;
import com.checkout.mobile.infra.persistence.product.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManagementImpl implements ProductGateway {

    @Value("${environment-url.url}")
    private String PATH;

    private final ProductRepository productRepository;

    public ProductManagementImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findByBarCode(String barCode) {
        ProductEntity response = productRepository.findProductByBarCode(barCode);

        return new Product(response.getId(),
                response.getName(),
                response.getPrice(),
                response.getBarCode(),
                response.getDescription(),
//                TODO NAO ENVIAR TODO A URL PARA O FRONT POIS ELA FICA EXPOSTA
                PATH + "/images/popular/" + response.getImgPath());
    }

    @Override
    public List<Product> getAll() {
        List<PopularProductsDTO> products = productRepository.findAllPopularProduct();
        return products.stream().map(productEntity -> new Product(
                productEntity.name(),
                productEntity.price(),
                PATH + "/images/popular/" + productEntity.imgPath())).toList();

    }
}
