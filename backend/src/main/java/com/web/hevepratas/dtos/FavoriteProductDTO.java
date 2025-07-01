package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.FavoriteProduct;
import lombok.Data;

@Data
public class FavoriteProductDTO {

    private Long id;
    private ProductDTO product;

    public FavoriteProductDTO() {}

    public FavoriteProductDTO(FavoriteProduct entity){
        this.id = entity.getId();
        this.product = new ProductDTO(entity.getProduct());
    }
}
