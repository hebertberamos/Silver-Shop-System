package com.web.hevepratas.services;

import com.web.hevepratas.entities.CartItem;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.repositories.CartItemRepository;
import com.web.hevepratas.repositories.ProductRepository;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import com.web.hevepratas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired ShoppingCartService shoppingCartService;

    public ResponseEntity<String> saveCartItem(Product productEntity, User userEntity, int quantity) throws Exception {

        //catch the cart item that have the product id and shopping cart id as the ones was passed.
        CartItem cartItemEntity = repository.findByProductIdAndShoppingCartId(productEntity.getId(), userEntity.getShoppingCart().getId());

        if(cartItemEntity == null){
            cartItemEntity = new CartItem();
            Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(userEntity.getShoppingCart().getId());
            ShoppingCart cartEntity = cartOptional.orElseThrow(() -> new Exception("Shopping cart id not found!"));

            cartItemEntity.setShoppingCart(cartEntity);
            cartItemEntity.setProduct(productEntity);
            cartItemEntity.setQuantity(quantity);

            repository.save(cartItemEntity);

            return shoppingCartService.addNewItemToCart(cartEntity, cartItemEntity);
        }

        cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);

        repository.save(cartItemEntity);
        return ResponseEntity.ok("Item quantity updated successfully!");
    }
}
