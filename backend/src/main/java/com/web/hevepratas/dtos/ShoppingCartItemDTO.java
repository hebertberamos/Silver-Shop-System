package com.web.hevepratas.dtos;

import lombok.Data;

@Data
public class ShoppingCartItemDTO {

    private long id;
    private ProductDTO product;
    private int quantity;

}
