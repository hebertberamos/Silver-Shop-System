package com.web.hevepratas.services;

import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;

    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){
        Page<User> page = repository.findAllUsers(pageable);
        return ResponseEntity.ok(page.map(entity -> userMapper.fromEntityToDto(entity)));
    }


    public String addNewUser(UserDTO dto) {
        User entity = userMapper.fromUserDtoToEntity(dto);

        try{
            ShoppingCart shoppingCart = new ShoppingCart(entity);
            entity.setShoppingCart(shoppingCart);

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
}
