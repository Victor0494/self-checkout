package com.checkout.mobile.infra.controller;

import com.checkout.mobile.application.usecases.ProductManagement;
import com.checkout.mobile.domain.entities.Product;
import com.checkout.mobile.infra.dto.PopularProductsDTO;
import com.checkout.mobile.infra.dto.ProductResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductManagement productManagement;
    private final ObjectMapper mapper;

    public ProductController(ProductManagement productManagement, ObjectMapper mapper) {
        this.productManagement = productManagement;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ProductResponseDTO> getProductByBarCode(@RequestParam(value = "barCode") String barCode){
        Product response = productManagement.getProductByBarCode(barCode);

        return ResponseEntity.ok(new ProductResponseDTO(
                response.getId(),
                response.getName(),
                response.getPrice(),
                response.getBarCode(),
                response.getDescription(),
                response.getImgPath()));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PopularProductsDTO>> getAllPopularProduct() {
        List<Product> productList = productManagement.getAllProduct();
        return ResponseEntity.ok(productList.stream().map(product -> mapper.convertValue(product, PopularProductsDTO.class)).toList());
    }

}
