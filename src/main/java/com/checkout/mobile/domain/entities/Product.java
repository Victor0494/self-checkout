package com.checkout.mobile.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private String barCode;
    private String description;
    private String imgPath;

    public Product(String id, String nome, BigDecimal price, String barCode, String description, String imgPath) {
        this.id = id;
        this.name = nome;
        this.price = price;
        this.barCode = barCode;
        this.description = description;
        this.imgPath = imgPath;
    }

    public Product(String name, BigDecimal price, String imgPath) {
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
