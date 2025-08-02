package com.web.hevepratas.security;

import com.web.hevepratas.entities.User;
import com.web.hevepratas.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = service.findByEmail(login);

        if(user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserEmail())
                .password(user.getUserPassword())
                .roles(String.valueOf(user.getUserRole()))
                .build();
    }

}
