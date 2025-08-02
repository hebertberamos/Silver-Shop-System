package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.User;
import com.web.hevepratas.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private UserRole userRole;
    private AddressDTO address;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPassword = user.getUserPassword();
        this.userRole = user.getUserRole();

        if(user.getAddress() != null) {
            this.address = new AddressDTO(user.getAddress());
        }
    }
}
