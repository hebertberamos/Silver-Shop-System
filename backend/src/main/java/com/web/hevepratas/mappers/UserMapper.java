package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromUserDtoToEntity(UserDTO dto){
        User entity = new User();
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
//        shopping cart

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
