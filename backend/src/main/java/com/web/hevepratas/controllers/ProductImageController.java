package com.web.hevepratas.controllers;

import com.web.hevepratas.servicies.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService service;

    @GetMapping("{productId}")
    public ResponseEntity<?> allByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.allImagesByProductId(productId));
    }


    @DeleteMapping("{imageId}")
    public ResponseEntity<?> delete(@PathVariable Long imageId) {
        return ResponseEntity.ok(service.delete(imageId));
    }
}
