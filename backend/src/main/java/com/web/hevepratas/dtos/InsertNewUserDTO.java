package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InsertNewUserDTO {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private String role;

    public InsertNewUserDTO() {}

    public InsertNewUserDTO(User entity){
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.email =  entity.getEmail();
        this.password = entity.getPassword();;
        this.role = entity.getRole().toString();
    }
}
