package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.LoginDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.responses.LoginResponse;
import com.web.hevepratas.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto){
        dto = authenticationService.register(dto);

        if(dto == null){
            return ResponseEntity.badRequest().body("Something went wrong to register the new user");
        }

        return ResponseEntity.ok("User registered successfully! \nUser email: " + dto.getEmail());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO dto){
        LoginResponse loginResponse = authenticationService.login(dto);
        return ResponseEntity.ok(loginResponse);
    }


}
