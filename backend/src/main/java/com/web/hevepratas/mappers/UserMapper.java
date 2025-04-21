package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.InsertNewUserDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.entities.enums.UserRole;
import com.web.hevepratas.repositories.AddressRepository;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public UserDTO fromEntityToDto(User entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        dto.setUserName(entity.getUserName());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    public User fromInsertNewUserDtoToEntity(InsertNewUserDTO dto){
        User entity = new User();

        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        switch (dto.getRoleNumber()) {
            case 0:
                entity.setRole(UserRole.ADMIN);
                break;
            case 1:
                entity.setRole(UserRole.MEMBER);
                break;
            case 2:
                entity.setRole(UserRole.USER);
                break;
        }

        return entity;
    }

    public InsertNewUserDTO fronEntityToInsertNewUserDto(User entity){
        InsertNewUserDTO dto = new InsertNewUserDTO();

        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        return dto;
    }

}
