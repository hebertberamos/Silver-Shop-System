package com.web.hevepratas.mappers;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address fromDtoToEntity(AddressDTO dto){
        Address entity = new Address();

        entity.setCity(dto.getCity());
        entity.setAddress(dto.getAddress());
        entity.setCep(dto.getCep());
        entity.setComplement(dto.getComplement());
        entity.setHouseNumber(dto.getHouseNumber());

        return entity;
    }

    public AddressDTO fromEntityToDto(Address entity){
        AddressDTO dto = new AddressDTO();

        dto.setCity(entity.getCity());
        dto.setAddress(entity.getAddress());
        dto.setCep(entity.getCep());
        dto.setComplement(entity.getComplement());
        dto.setHouseNumber(entity.getHouseNumber());

        return dto;
    }

}
