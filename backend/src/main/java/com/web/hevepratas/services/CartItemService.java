package com.web.hevepratas.services;

import com.web.hevepratas.entities.CartItem;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ShoppingCart;
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

    public ResponseEntity<String> saveCartItem(Long productId, ShoppingCart cartEntity, int quantity) throws Exception {
        CartItem cartItemEntity = new CartItem();

        Optional<Product> productOptional = productRepository.findById(productId);
        Product productEntity = productOptional.orElseThrow(() -> new Exception("product id not found"));

        cartItemEntity.setShoppingCart(cartEntity);
        cartItemEntity.setProduct(productEntity);
        cartItemEntity.setQuantity(quantity);

        repository.save(cartItemEntity);

        return shoppingCartService.addCartItemToCart(cartEntity.getId(), cartItemEntity);

    }
}
