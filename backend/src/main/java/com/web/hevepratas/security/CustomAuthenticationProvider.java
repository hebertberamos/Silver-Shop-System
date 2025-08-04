package com.web.hevepratas.security;

import com.web.hevepratas.entities.User;
import com.web.hevepratas.exceptions.LoginNotFoundException;
import com.web.hevepratas.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService service;
    private final PasswordEncoder encoder;

    // This method will be used to verify an authentication and check if the user can be authenticated to return this authentication object.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String typedPassword = authentication.getCredentials().toString();

        User authenticatedUser = service.findByEmail(login);

        if(authenticatedUser == null) {
            throw loginException();
        }

        String encodedPassword = authenticatedUser.getUserPassword();

        boolean machPasswords = encoder.matches(typedPassword, encodedPassword);

        if(!machPasswords){
            throw loginException();
        }

        return new CustomAuthentication(authenticatedUser);
    }

    private LoginNotFoundException loginException() {
        return new LoginNotFoundException("Login ou senha incorreto...");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
