package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String city;
    private String street;
    private String cep;
    private String complement;
    private int houseNumber;
//    private User user;


    public AddressDTO () {}

    public AddressDTO (Address entity)  {
        this.id = entity.getId();
        this.city = entity.getCity();
        this.street = entity.getStreet();
        this.cep = entity.getCep();
        this.complement = entity.getComplement();
        this.houseNumber = entity.getHouseNumber();
//        this.user = entity.getUser();
    }

}
