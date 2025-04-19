package com.web.hevepratas.services;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.dtos.InsertNewUserDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.mappers.UserMapper;
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
            entity.setShoppingCart(shoppingCart);

            entity.setPassword(passwordEncoder.encode(entity.getPassword()));

            repository.save(entity);

            return "New user saved";
        }
        catch(Exception e){
            return "Error to save user. \n" + e;
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        try{
            repository.deleteById(id);
            return ResponseEntity.ok("User deleted");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error while deleting user");
        }
    }

    public ResponseEntity<String> addNewAddress( AddressDTO dto) throws Exception {

        UserDTO userDto = authenticationService.authenticatedUser();
        User userEntity = userMapper.fromUserDtoToEntity(userDto);

        return addressService.saveNewAddress(dto, userEntity);
    }

//    public UserDTO getUserByEmail(String email) {
//
//        User userEntity = repository.findByEmail(email);
//        return userMapper.fromEntityToDto(userEntity);
//
//    }
}
