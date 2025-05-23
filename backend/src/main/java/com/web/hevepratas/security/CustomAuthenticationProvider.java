package com.web.hevepratas.security;

import com.web.hevepratas.entities.User;
import com.web.hevepratas.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String rowPassword = authentication.getCredentials().toString();

        User entity = repository.findByEmail(email);

        if(entity == null)
            throw new UsernameNotFoundException("User not found");

        String encodedPassword = entity.getPassword();

        if(!encoder.matches(rowPassword, encodedPassword))
            throw new UsernameNotFoundException("User not found");

        return new CustomAuthentication(entity);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
