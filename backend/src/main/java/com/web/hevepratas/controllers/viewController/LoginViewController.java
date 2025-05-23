package com.web.hevepratas.controllers.viewController;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping
    @ResponseBody
    public String home(Authentication authentication) {
        return "Hi " + authentication.getName();
    }

}
