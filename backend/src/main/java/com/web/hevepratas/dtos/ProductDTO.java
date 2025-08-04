package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.Product;
import com.web.hevepratas.enums.ProductGender;
import com.web.hevepratas.enums.ProductSubType;
import com.web.hevepratas.enums.ProductType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String productName;
    private BigDecimal productPrice;
    private ProductGender productGender;
    private ProductType productType;
    private ProductSubType productSubType;
    private BigDecimal productSize;
    private String productDescription;

    public ProductDTO(){}

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.productName = entity.getProductName();
        this.productPrice = entity.getProductPrice();
        this.productGender = entity.getProductGender();
        this.productType = entity.getProductType();
        this.productSubType = entity.getProductSubType();
        this.productSize = entity.getProductSize();
        this.productDescription = entity.getProductDescription();
    }
}
