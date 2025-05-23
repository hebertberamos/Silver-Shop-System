package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.dtos.InsertNewUserDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.services.AddressService;
import com.web.hevepratas.services.AuthenticationService;
import com.web.hevepratas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController implements GenericController{

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    AddressService addressService;


    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAllUser(Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Object> addNewUser(@RequestBody InsertNewUserDTO dto){
        InsertNewUserDTO userDto = service.addNewUser(dto);

        if(userDto != null){
            URI location = generateResponse(userDto.getId());
            return ResponseEntity.created(location).body(userDto);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }


    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> authenticatedUser(){
        try {

            UserDTO currentUser = service.personalProfile();

            return ResponseEntity.ok(currentUser);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    //This guy will be implemented using the jwt to get the user, so the {userId} will not be passed because the application will know the user by the token.
    @PostMapping(value = "/address")
    public ResponseEntity<String> addNewAddress(@RequestBody AddressDTO dto) throws Exception {
        return addressService.saveNewAddress(dto);
    }
}
