package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.Address;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.repositories.AddressRepository;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private AddressRepository addressRepository;


    public User fromUserDtoToEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setRole(dto.getRole());
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        try {
            Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(dto.getShoppingCartId());
            ShoppingCart shoppingCartEntity = shoppingCartOptional.orElseThrow(() -> new Exception("Shopping cart id not found"));

            entity.setShoppingCart(shoppingCartEntity);

            if(dto.getAddressId() != null) {
                Optional<Address> addressOptional = addressRepository.findById(dto.getAddressId());
                Address addressEntity = addressOptional.orElseThrow(() -> new Exception("Shopping cart id not found"));

                entity.setAddress(addressEntity);
            } else {
                entity.setAddress(null);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return entity;
    }

    public UserDTO fromEntityToDto(User entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setShoppingCartId(entity.getShoppingCart().getId());

        if(entity.getAddress() != null){
            dto.setAddressId(entity.getAddress().getId());
        } else {
            dto.setAddressId(null);
        }

        return dto;
    }

}
