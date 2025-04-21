package com.web.hevepratas.services;

import com.web.hevepratas.dtos.InsertNewUserDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import com.web.hevepratas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){
        Page<User> page = repository.findAllUsers(pageable);
        return ResponseEntity.ok(page.map(entity -> userMapper.fromEntityToDto(entity)));
    }


    public String addNewUser(InsertNewUserDTO dto) {
        User entity = userMapper.fromInsertNewUserDtoToEntity(dto);

        try{
            ShoppingCart shoppingCart = new ShoppingCart(entity);

            entity.setPassword(passwordEncoder.encode(entity.getPassword()));

            shoppingCartRepository.save(shoppingCart);

            return "New user saved";
        }
        catch(Exception e){
            return "Error to save user. \n" + e;
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        try{

            Optional<User> optional = repository.findById(id);
            User entity = optional.orElseThrow();
            ShoppingCart shoppingCartEntity = shoppingCartRepository.findByUser(entity);

            // Shopping cart already deletes user that is related because of CascadeType.ALL
            shoppingCartRepository.deleteById(shoppingCartEntity.getId());

            return ResponseEntity.ok("User deleted");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error while deleting user");
        }
    }

    public UserDTO personalProfile() {

        User currentUser = authenticationService.authenticatedUser();
        authenticationService.validateSelfOrAdmin(currentUser.getEmail());

        return userMapper.fromEntityToDto(currentUser);
    }

}
