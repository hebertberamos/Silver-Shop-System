package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.entities.Product;

public class ProductMapper {

    public static Product mapToProduct(ProductDTO dto) {
        Product returnProduct = new Product();

        returnProduct.setProductName(dto.getProductName());
        returnProduct.setProductPrice(dto.getProductPrice());
        returnProduct.setProductGender(dto.getProductGender());
        returnProduct.setProductType(dto.getProductType());
        returnProduct.setProductSubType(dto.getProductSubType());
        returnProduct.setProductSize(dto.getProductSize());
        returnProduct.setProductDescription(dto.getProductDescription());

        return returnProduct;
    }

}
