package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.dtos.InsertNewUserDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.services.AuthenticationService;
import com.web.hevepratas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
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

    @GetMapping(value = "/all")
    public ResponseEntity<Page<UserDTO>> findAllUser(Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> addNewUser(@RequestBody InsertNewUserDTO dto){
        return ResponseEntity.ok(service.addNewUser(dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

    //This guy will be implemented using the jwt to get the user, so the {userId} will not be passed because the application will know the user by the token.
    @PostMapping(value = "/add/address")
    public ResponseEntity<String> addNewAddress(@RequestBody AddressDTO dto) throws Exception {
        return service.addNewAddress(dto);
    }

    @GetMapping(value = "/test")
    public String ping(){
        return "pong";
    }

}
