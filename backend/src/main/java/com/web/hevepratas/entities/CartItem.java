package com.web.hevepratas.entities;

import jakarta.persistence.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "cart_item_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "itemsQuantity")
    private Integer quantity;

}
