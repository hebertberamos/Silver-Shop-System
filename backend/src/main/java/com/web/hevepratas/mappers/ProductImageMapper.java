package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.ProductImageDTO;
import com.web.hevepratas.entities.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    public ProductImage fromDtoToEntity(ProductImageDTO dto){

        ProductImage entity = new ProductImage();

        entity.setImageName(dto.getImageName());
        entity.setMainImage(dto.isMainImage());

        return entity;
    }

    public ProductImageDTO fromEntityToDto(ProductImage entity){

        ProductImageDTO dto = new ProductImageDTO();

        dto.setImageName(entity.getImageName());
        dto.setMainImage(entity.isMainImage());

        return dto;

    }

}
