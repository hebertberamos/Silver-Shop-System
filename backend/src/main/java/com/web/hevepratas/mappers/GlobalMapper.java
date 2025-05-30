package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.Address;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.User;

public class GlobalMapper {

    public static User mapToUser(UserDTO dto) {
        User entity =  new User();

        entity.setUserName(dto.getUserName());
        entity.setUserEmail(dto.getUserEmail());
        entity.setUserPassword(dto.getUserPassword());
        entity.setUserRole(dto.getUserRole());
        entity.setAddress(mapToAddress(dto.getAddress()));

        return entity;

    }

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

    public static Address mapToAddress(AddressDTO dto) {
        Address returnAddress = new Address();

        returnAddress.setCity(dto.getCity());
        returnAddress.setStreet(dto.getStreet());
        returnAddress.setCep(dto.getCep());
        returnAddress.setComplement(dto.getComplement());
        returnAddress.setHouseNumber(dto.getHouseNumber());

        return returnAddress;
    }
}
