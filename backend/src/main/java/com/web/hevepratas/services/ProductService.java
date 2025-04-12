package com.web.hevepratas.services;

import com.web.hevepratas.dtos.InsertNewProductDTO;
import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ProductImage;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.entities.enums.Gender;
import com.web.hevepratas.entities.enums.ProductSubType;
import com.web.hevepratas.entities.enums.ProductType;
import com.web.hevepratas.mappers.ProductImageMapper;
import com.web.hevepratas.mappers.ProductMapper;
import com.web.hevepratas.mappers.UserMapper;
import com.web.hevepratas.repositories.ProductRepository;
import com.web.hevepratas.repositories.ShoppingCartRepository;
import com.web.hevepratas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private ProductImageService productImageService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductImageMapper productImageMapper;

    public Page<ProductDTO> allProducts(ProductType type, ProductSubType subType, Gender gender, Pageable pageable) {
        Page<Product> page = repository.findAllProducts(type, subType, gender, pageable);

        return page.map(entity -> mapper.fromEntityToDto(entity));
    }

    public ResponseEntity<ProductDTO> findById(Long id) throws Exception {
        Optional<Product> optionalProduct = repository.findById(id);
        Product productEntity = optionalProduct.orElseThrow(() -> new Exception("Error trying to find product by id " + id));

        if(productEntity != null){
            ProductDTO dto = mapper.fromEntityToDto(productEntity);
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<String> saveNewProduct(InsertNewProductDTO dto) {
        try {
            Product entity = mapper.fromInsertNewProductDtoToEntity(dto);

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

    public ResponseEntity<?> processProductPurchase(int quantity, Long productId) {
        try {
            //Get the user.
            UserDTO userDto = authenticationService.authenticatedUser();
            User userEntity = userMapper.fromUserDtoToEntity(userDto);

            //Check if user already have an address instantiated for your self
            if(userEntity.getAddress() == null) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body("Você precisa de um endereço para efetuar sua compra. Vamos adicionar um!");
            }

            //Get product by id
            Optional<Product> optional = repository.findById(productId);
            Product productEntity = optional.orElseThrow(() -> new Exception("Infelizmente o com o id " + productId + " não foi encontrado..."));

            //Reduce the quantity of products
            int updatedAvailableQuantity = productEntity.getQuantityAvailable() - quantity;
            productEntity.setQuantityAvailable(updatedAvailableQuantity);

            //if all is good, email user about the purchase

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Product purchased");
    }

    public ResponseEntity<String> addImageToProduct(Long productId, MultipartFile mainImage, List<MultipartFile> images) throws Exception {
        return productImageService.saveImages(mainImage, images, productId);
    }
}
