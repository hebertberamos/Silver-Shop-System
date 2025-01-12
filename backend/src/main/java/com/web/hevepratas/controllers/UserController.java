package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.UserDTO;
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

    @GetMapping(value = "/all")
    public ResponseEntity<Page<UserDTO>> findAllUser(Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> addNewUser(@RequestBody UserDTO dto){
        return ResponseEntity.ok(service.addNewUser(dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

}
