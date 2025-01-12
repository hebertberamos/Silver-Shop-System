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
        entity.setPassword(dto.getEmail());
//        shopping cart
        entity.setCity(dto.getCity());
        entity.setUserAddress(dto.getUserAddress());
        entity.setCep(dto.getCep());
        entity.setComplement(dto.getComplement());
        entity.setHouseNumber(dto.getHouseNumber());
        entity.setNeighborhood(dto.getNeighborhood());

        return entity;
    }

    public UserDTO fromEntityToDto(User entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getEmail());
//        shopping cart
        dto.setCity(entity.getCity());
        dto.setUserAddress(entity.getUserAddress());
        dto.setCep(entity.getCep());
        dto.setComplement(entity.getComplement());
        dto.setHouseNumber(entity.getHouseNumber());
        dto.setNeighborhood(entity.getNeighborhood());

        return dto;
    }

}
