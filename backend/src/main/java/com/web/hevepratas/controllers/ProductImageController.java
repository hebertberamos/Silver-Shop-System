package com.web.hevepratas.controllers;

import com.web.hevepratas.servicies.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService service;

    @GetMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> allByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.allImagesByProductId(productId));
    }


    @DeleteMapping("{imageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long imageId, Authentication auth) {
        return ResponseEntity.ok(service.delete(imageId, auth));
    }
}
