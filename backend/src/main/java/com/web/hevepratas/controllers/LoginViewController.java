package com.web.hevepratas.controllers;

import com.web.hevepratas.security.CustomAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginViewController {
    
    @GetMapping
    public String homePage(Authentication authentication){
        if(authentication instanceof CustomAuthentication customAuth){
            System.out.println(customAuth.getUser().getUserEmail());
        }

        return "Olá " + authentication.getName();
    }

}
