package com.checkout.mobile.infra.persistence.client;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_clients")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientEntity {

    @Id
    private String id;
    private String name;
    private String document;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CartEntity> cart;

    public void addCart(CartEntity cart) {
        if (cart == null) return;
        if (this.cart == null) this.cart = new ArrayList<>();
        this.cart.add(cart);
        cart.setClient(this);
    }
}
