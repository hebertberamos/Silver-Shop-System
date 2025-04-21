package com.web.hevepratas.services;

import com.web.hevepratas.entities.FavoriteList;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.FavoriteListRepository;
import com.web.hevepratas.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteListService {

    @Autowired
    private FavoriteListRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;


    public ResponseEntity<?> favoriteProduct(Long productId) throws Exception {
        try {
            User userEntity = authenticationService.authenticatedUser();

            Optional<Product> optionalProduct = productRepository.findById(productId);
            Product productEntity = optionalProduct.orElseThrow(() -> new Exception("Error to find product with id " + productId));

            FavoriteList favoriteList = new FavoriteList();
            favoriteList.setUser(userEntity);
            favoriteList.getFavoriteProducts().add(productEntity);

            repository.save(favoriteList);

            return ResponseEntity.ok("Produto adicionado a favoritos!");
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
