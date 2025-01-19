package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.LoginDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.responses.LoginResponse;
import com.web.hevepratas.services.AuthenticationService;
import com.web.hevepratas.services.JwtService;
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
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto){
        User registeredUser = authenticationService.register(dto);

        return ResponseEntity.ok("User registered successfully! \nUser email: " + registeredUser.getEmail());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO dto){
        User userAuthenticated = authenticationService.login(dto);

        String token = jwtService.generateToken(userAuthenticated);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }


}
