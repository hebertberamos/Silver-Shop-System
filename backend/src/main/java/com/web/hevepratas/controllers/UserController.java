package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.services.AuthenticationService;
import com.web.hevepratas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationService authenticationService;

    //just to test
    @GetMapping(value = "/personal-profile")
    public ResponseEntity<UserDTO> authenticatedUser(){
        try {
            UserDTO currentUser = authenticationService.authenticatedUser();

            authenticationService.validateSelfOrAdmin(currentUser.getEmail());

            return ResponseEntity.ok(currentUser);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping(value = "/get/{email}")
//    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
//        UserDTO dto = service.getUserByEmail(email);
//        if(dto == null){
//            return ResponseEntity.badRequest().build();
//        }
//
//        return ResponseEntity.ok(dto);
//    }


    //This guy will be implemented using the jwt to get the user, so the {userId} will not be passed because the application will know the user by the token.
    @PostMapping(value = "/add/address")
    public ResponseEntity<String> addNewAddress(@RequestBody AddressDTO dto) throws Exception {
        return service.addNewAddress(dto);
    }

}
