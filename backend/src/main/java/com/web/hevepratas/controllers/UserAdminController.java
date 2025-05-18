package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.InsertNewUserDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/admin")
public class UserAdminController implements GenericController{

    @Autowired
    UserService service;


    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserDTO>> findAllUser(Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Object> addNewUser(@RequestBody InsertNewUserDTO dto){
        InsertNewUserDTO userDto = service.addNewUser(dto);

        if(userDto != null){
            URI location = generateResponse(userDto.getId());
            return ResponseEntity.created(location).body(userDto);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

}
