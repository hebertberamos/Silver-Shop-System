package com.web.hevepratas.servicies;

import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository repository;

    protected void create(User user) {
        ShoppingCart cart = new ShoppingCart(user);
        repository.save(cart);
    }

    protected ShoppingCart getCartByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

}
