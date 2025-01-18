package com.web.hevepratas.dtos;

import java.util.List;

public class ShoppingCartDTO {

    private Integer id;
    private Long userId;
    private List<CartItemDTO> items;
    private Integer itemsQuantity;
    private Double cartAmount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public Double getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(Double cartAmount) {
        this.cartAmount = cartAmount;
    }
}
