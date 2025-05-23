package com.web.hevepratas.security;

import com.web.hevepratas.entities.User;
import com.web.hevepratas.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = repository.findByEmail(username);
        System.out.println(entity.getEmail());

        if(entity == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(entity.getEmail())
                .password(entity.getPassword())
                .roles(String.valueOf(entity.getRole()))
                .build();
    }
}
