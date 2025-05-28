package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.User;

public final class UserMapper {

    public static User mapToUser(UserDTO dto) {
        User entity =  new User();

        entity.setUserName(dto.getUserName());
        entity.setUserEmail(dto.getUserEmail());
        entity.setUserPassword(dto.getUserPassword());
        entity.setUserRole(dto.getUserRole());

        return entity;

    }

}
