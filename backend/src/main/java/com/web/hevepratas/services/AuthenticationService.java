package com.web.hevepratas.services;

import com.web.hevepratas.dtos.LoginDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.entities.enums.UserRole;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    public User register(UserDTO dto){
        User userEntity = userMapper.fromUserDtoToEntity(dto);
        String encryptedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encryptedPassword);
        userEntity.setRole(UserRole.USER);

        ShoppingCart shoppingCart = new ShoppingCart(userEntity);
        userEntity.setShoppingCart(shoppingCart);

        return userRepository.save(userEntity);
    }

    public User login(LoginDTO dto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        return userRepository.findByEmail(dto.getEmail());
    }

    public User authenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }
}
