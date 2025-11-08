package com.checkout.mobile.domain.entities;


import java.util.Collections;
import java.util.List;

public class Client {

    private String id;
    private String name;
    private String document;
    private List<Cart> cart;
    private String password;

    public Client() {
    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String name, String document, String password) {
        this.name = name;
        this.document = document;
        this.password = password;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public void addCart(Cart cart) {
        this.cart.add(cart);
    }
}
