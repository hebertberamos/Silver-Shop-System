package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.entities.Address;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public AddressDTO findByUserId(Long userId) {
        return new AddressDTO(catchAddressByUserId(userId));
    }


    public AddressDTO update(Long userId, AddressDTO dtoBody) {
        Address addressObject = catchAddressByUserId(userId);

        if(addressObject == null){
            throw new RuntimeException("O usuário com id " + userId + " foi encontrado, mas algo deu errado. Ele veio como null.");
        }

        addressObject.setCity(dtoBody.getCity());
        addressObject.setStreet(dtoBody.getStreet());
        addressObject.setCep(dtoBody.getCep());
        addressObject.setComplement(dtoBody.getComplement());
        addressObject.setHouseNumber(dtoBody.getHouseNumber());

        repository.save(addressObject);

        return new AddressDTO(addressObject);
    }


    private Address catchAddressByUserId(Long userId) {
       return repository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Não conseguimso encontrar nenhum usuário com o id " + userId));
    }

}
