package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.InsertNewProductDTO;
import com.web.hevepratas.dtos.PageDTO;
import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.entities.enums.Gender;
import com.web.hevepratas.entities.enums.ProductSubType;
import com.web.hevepratas.entities.enums.ProductType;
import com.web.hevepratas.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/all")
    public ResponseEntity<PageDTO<ProductDTO>> allProducts(
            @RequestParam (value = "type", required = false) ProductType type,
            @RequestParam (value = "sub_type", required = false) ProductSubType subType,
            @RequestParam (value = "gender", required = false) Gender gender,
            Pageable pageable){

        Page<ProductDTO> page = service.allProducts(type, subType, gender, pageable);
        PageDTO<ProductDTO> response = new PageDTO<>(page);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> saveNewProduct(@RequestBody InsertNewProductDTO dto){
        return service.saveNewProduct(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) throws Exception {
        return service.findById(id);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) throws Exception {
        return service.updateProduct(id, dto);
    }

    @PostMapping(value = "/add/images")
    public ResponseEntity<String> addImagesToProduct(@RequestParam("productId") Long productId, @RequestParam("mainImage") MultipartFile mainImage, @RequestParam("images") List<MultipartFile> images) {
        try {
            if (productId == null) {
                return ResponseEntity.badRequest().body("Product not specified. To do it, please chose a product.");
            }

            return service.addImageToProduct(productId, mainImage, images);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add/{productId}/to-card")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Long productId,
            @RequestParam (required = true, defaultValue = "1") int quantity
    ) throws Exception
    {

        return service.addProductToCart(productId, quantity);
    }

    @PostMapping("/buy/{productId}")
    public ResponseEntity<?> buyProduct(@PathVariable Long productId, @RequestParam (required = true, defaultValue = "1") int quantity){
        return service.processProductPurchase(quantity, productId);
    }

}
