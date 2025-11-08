package com.checkout.mobile.domain.entities;

import com.checkout.mobile.domain.valueObject.CartStatus;
import com.checkout.mobile.domain.valueObject.Item;
import com.checkout.mobile.infra.persistence.client.ClientEntity;
import com.checkout.mobile.infra.persistence.client.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private String id;
    private List<Item> items;
    private Float totalValue;
    private CartStatus status;
    private LocalDateTime dateTime;
    private Client client;


    public Cart(String id, List<Item> items, Float totalValue, CartStatus status, Client client) {
        this.id = id;
        this.items = items;
        this.totalValue = totalValue;
        this.status = status;
        this.client = client;
    }

    public Cart(String id, List<Item> items, Float totalValue) {
        this.id = id;
        this.items = new ArrayList<>(items);
        this.totalValue = totalValue;
    }

    public Cart() {

    }

    public void addItem(Item item) {
        if(item.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Optional<Item> exist = this.items.stream()
                .filter(listItem -> listItem.getProductId().equals(item.getProductId()))
                .findFirst();

        if(exist.isPresent()) {
            exist.get().increaseQuantity(item.getQuantity());
        } else {
            this.items.add(item);
        }
        updateTotalValue(item);
    }

    private void updateTotalValue(Item item) {
        this.totalValue = (float) this.items.stream()
                .mapToDouble(i -> i.getPricePerUnity() * i.getQuantity())
                .sum();    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
