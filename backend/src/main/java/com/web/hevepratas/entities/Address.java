package com.web.hevepratas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "O nome da cidade é obrigatório")
    @Column(name = "address_city")
    private String city;

    @NotBlank(message = "O nome da rua é obrigatório")
    @Column(name = "address_street")
    private String street;

    @NotBlank(message = "O cep é obrigatório")
    @Column(name = "address_cep")
    private String cep;

    @Column(name = "address_complement")
    private String complement;

    @Column(name = "address_house_number")
    private int houseNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
