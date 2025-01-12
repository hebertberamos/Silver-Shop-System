package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.CartItem;
import com.web.hevepratas.entities.User;
import java.util.List;

public class ShoppingCartDTO {

    private Integer id;
//    private User user;
//    private List<CartItem> items;
    private Integer itemsQuantity;
    private Double cartAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
