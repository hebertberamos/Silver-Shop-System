package com.web.hevepratas.services;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.entities.Address;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.mappers.AddressMapper;
import com.web.hevepratas.repositories.AddressRepository;
import com.web.hevepratas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {


    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> saveNewAddress(AddressDTO dto) {
        try {
            User userEntity  = authenticationService.authenticatedUser();

            Address addressEntity = addressMapper.fromDtoToEntity(dto);
            addressEntity.setUser(userEntity);

            repository.save(addressEntity);

            return ResponseEntity.ok("New address saved successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
