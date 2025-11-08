package com.checkout.mobile.infra.persistence.client;

import com.checkout.mobile.domain.entities.Client;
import com.checkout.mobile.domain.valueObject.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_carts")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemEntity> items = new ArrayList<>();

    private Float totalValue;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

}
