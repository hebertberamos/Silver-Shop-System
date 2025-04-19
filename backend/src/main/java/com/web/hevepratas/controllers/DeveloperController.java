package com.web.hevepratas.controllers;


import com.web.hevepratas.dtos.InsertNewUserDTO;
import com.web.hevepratas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dev")
public class DeveloperController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/new")
    public ResponseEntity<String> addNewUser(@RequestBody InsertNewUserDTO dto){
        return ResponseEntity.ok(userService.addNewUser(dto));
    }

}
