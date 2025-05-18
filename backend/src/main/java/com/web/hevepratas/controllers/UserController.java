package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.services.AddressService;
import com.web.hevepratas.services.AuthenticationService;
import com.web.hevepratas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    AddressService addressService;
    //just to test
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

    // method to buy product may need to be added here
}
