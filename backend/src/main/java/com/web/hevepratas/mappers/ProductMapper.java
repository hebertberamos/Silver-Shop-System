package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.dtos.ProductImageDTO;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductMapper {

    @Autowired
    ProductImageMapper productImageMapper;

    public Product fromDtoToEntity(ProductDTO dto){
        Product entity = new Product();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setGender(dto.getGender());
        entity.setType(dto.getType());
        entity.setSubType(dto.getSubType());
        entity.setDescription(dto.getDescription());
        entity.setSize(dto.getSize());
        entity.setQuantityAvailable(dto.getQuantityAvailable());



        return entity;
    }

    public ProductDTO fromEntityToDto(Product entity){
        ProductDTO dto = new ProductDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setGender(entity.getGender());
        dto.setType(entity.getType());
        dto.setSubType(entity.getSubType());
        dto.setDescription(entity.getDescription());
        dto.setSize(entity.getSize());
        dto.setQuantityAvailable(entity.getQuantityAvailable());

        List<ProductImage> productImages = entity.getImages();

        if(!entity.getImages().isEmpty()){
            for(ProductImage image : productImages){
<<<<<<< Updated upstream
=======
                if(image.isMainImage()){
                    dto.setMainImageUrl(image.getImageUrl());
                }
>>>>>>> Stashed changes
                dto.getImages().add(productImageMapper.fromEntityToDto(image));
            }
        }

        return dto;
    }

}
