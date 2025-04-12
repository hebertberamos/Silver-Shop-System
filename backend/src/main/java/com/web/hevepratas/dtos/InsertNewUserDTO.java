package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InsertNewUserDTO {

    private String userName;
    private String email;
    private String password;
    private int roleNumber;


}
