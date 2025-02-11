package com.web.hevepratas.services;

import com.web.hevepratas.dtos.LoginDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.entities.enums.UserRole;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.UserRepository;
import com.web.hevepratas.responses.LoginResponse;
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
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    public UserDTO register(UserDTO dto){
        boolean emailAlreadyExist = validateEmailAlreadyExistInDatabase(dto.getEmail());

        if(emailAlreadyExist == false) {
            try {
                User userEntity = userMapper.fromUserDtoToEntity(dto);
                String encryptedPassword = passwordEncoder.encode(userEntity.getPassword());
                userEntity.setPassword(encryptedPassword);
                userEntity.setRole(UserRole.USER);

                ShoppingCart shoppingCart = new ShoppingCart(userEntity);
                userEntity.setShoppingCart(shoppingCart);

                userRepository.save(userEntity);

                return userMapper.fromEntityToDto(userEntity);
            }
            catch(Exception e){
                System.out.println("LOG - Error trying to save new user");
            }
        }

        return null;
    }

    public LoginResponse login(LoginDTO dto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));

        User userAuthenticated = userRepository.findByEmail(dto.getEmail());

        String token = jwtService.generateToken(userAuthenticated);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }

    public UserDTO authenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return userMapper.fromEntityToDto(currentUser);

    }


    private boolean validateEmailAlreadyExistInDatabase(String dtoEmail){
        User userValidator = userRepository.findByEmail(dtoEmail);

        if(userValidator != null){
            return true;
        }

        return false;
    }

    public void validateSelfOrAdmin(String email){
        User user = userMapper.fromUserDtoToEntity(authenticatedUser());

        if(!user.getEmail().equals(email) || user.getRole() != UserRole.ADMIN){
            System.out.println("Access denied"); // this guy will be changed to a personal Exception. MODIFY
        }
    }
}
