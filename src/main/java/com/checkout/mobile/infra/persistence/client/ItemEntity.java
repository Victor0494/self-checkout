package com.checkout.mobile.infra.persistence.client;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_items")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String productId;
    private String name;
    private Integer quantity;
    private Float pricePerUnity;
    private String imgPath;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;
}
