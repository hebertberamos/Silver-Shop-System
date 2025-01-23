package com.web.hevepratas.services;

import com.web.hevepratas.entities.CartItem;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;
    @Autowired
    private UserMapper userMapper;

    public ShoppingCart saveNewShoppingCart(User entity){
        ShoppingCart cart = new ShoppingCart(entity);
        return repository.save(cart);
    }

    public ResponseEntity<String> addNewItemToCart(ShoppingCart cartEntity, CartItem cartItemEntity) {
        List<CartItem> items = cartEntity.getItems();
        items.add(cartItemEntity);

        return ResponseEntity.ok("New item saved to cart with id (" + cartEntity.getId() + ")");
    }

}
