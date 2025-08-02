package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.ProductImage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductImageDTO {

    private Long id;
    private String imageName;
    private boolean mainImage;

    public ProductImageDTO (ProductImage image) {
        this.id = image.getId();
        this.imageName = image.getImageName();
        this.mainImage = image.isMainImage();
    }
}
