package com.web.hevepratas.entities;

import com.web.hevepratas.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "O email do usuário é obrigatório")
    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @NotBlank(message = "A senha do usuário é obrigatória")
    @Column(name="user_password", nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Address address;


    public void setAddress(Address address) {
        this.address = address;
        address.setUser(this);
    }
}
