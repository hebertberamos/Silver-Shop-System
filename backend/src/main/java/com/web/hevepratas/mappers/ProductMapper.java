package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductMapper {

    public Product fromDtoToEntity(ProductDTO dto){
        Product entity = new Product();

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

        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setGender(entity.getGender());
        dto.setType(entity.getType());
        dto.setSubType(entity.getSubType());
        dto.setDescription(entity.getDescription());
        dto.setSize(entity.getSize());
        dto.setQuantityAvailable(entity.getQuantityAvailable());

        return dto;
    }

}
