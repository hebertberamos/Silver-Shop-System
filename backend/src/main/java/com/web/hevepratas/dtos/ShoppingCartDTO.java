package com.web.hevepratas.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {

    private Long id;
    private UserDTO user;
    private List<ShoppingCartItemDTO> items;
    private int itemsQuantity;
    private double subtotal;

}
