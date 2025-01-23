package com.web.hevepratas.services;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.entities.enums.Gender;
import com.web.hevepratas.entities.enums.ProductSubType;
import com.web.hevepratas.entities.enums.ProductType;
import com.web.hevepratas.mappers.ProductMapper;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.ProductRepository;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import com.web.hevepratas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;

    public ResponseEntity<Page<ProductDTO>> allProducts(ProductType type, ProductSubType subType, Gender gender, Pageable pageable) {
        Page<Product> page = repository.findAllProducts(type, subType, gender, pageable);

        return ResponseEntity.ok(page.map(entity -> mapper.fromEntityToDto(entity)));
    }

    public ResponseEntity<String> addNewProduct(ProductDTO dto) {
        try {
            Product entity = mapper.fromDtoToEntity(dto);
            repository.save(entity);
            return ResponseEntity.ok("Product saved successfully");
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Error to save product \n" + e);
        }

    }

    public ResponseEntity<String> updateProduct(Long id, ProductDTO dto) throws Exception {

        try {
            Optional<Product> optional = repository.findById(id);
            Product entity = optional.orElseThrow(() -> new Exception("Id not found"));

            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            entity.setDescription(dto.getDescription());
            entity.setQuantityAvailable(dto.getQuantityAvailable());

            repository.save(entity);

            return ResponseEntity.ok("Product updated successfully!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error to save update... \n" + e);
        }
    }

    public ResponseEntity<String> addProductToCart(Long id,  int quantity) throws Exception {

        UserDTO userDto = authenticationService.authenticatedUser();
        User userEntity = userMapper.fromUserDtoToEntity(userDto);

        Optional<Product> productOptional = repository.findById(id);
        Product productEntity = productOptional.orElseThrow(() -> new Exception("product id not found"));

        // Here I'll paste a user entity and a product entity
        return cartItemService.saveCartItem(productEntity, userEntity, quantity);
    }
}
