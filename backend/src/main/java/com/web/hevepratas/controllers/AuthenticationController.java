package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.LoginDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.responses.LoginResponse;
import com.web.hevepratas.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto){
        return ResponseEntity.ok(authenticationService.register(dto));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO dto){
        LoginResponse loginResponse = authenticationService.login(dto);
        return ResponseEntity.ok(loginResponse);
    }


}
