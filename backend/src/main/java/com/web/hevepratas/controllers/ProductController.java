package com.web.hevepratas.controllers;

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

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/all")
    public ResponseEntity<Page<ProductDTO>> allProducts(
            @RequestParam (value = "type", required = false) ProductType type,
            @RequestParam (value = "sub_type", required = false) ProductSubType subType,
            @RequestParam (value = "gender", required = false) Gender gender,
            Pageable pageable){
        return service.allProducts(type, subType, gender, pageable);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> addNewProduct(@RequestBody ProductDTO dto){
        return service.addNewProduct(dto);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) throws Exception {
        return service.updateProduct(id, dto);
    }

    @PostMapping("/add/{productId}/to-card")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Long productId,
            @RequestParam (required = true, defaultValue = "1") int quantity
    ) throws Exception
    {

        return service.addProductToCart(productId, quantity);
    }

}
