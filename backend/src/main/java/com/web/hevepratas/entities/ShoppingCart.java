package com.web.hevepratas.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "shopping_cart_id")
    private Integer id;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    private Integer itemsQuantity;
    private Double cartValue;

}
