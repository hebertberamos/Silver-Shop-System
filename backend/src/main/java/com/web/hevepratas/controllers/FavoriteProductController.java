package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.FavoriteProductDTO;
import com.web.hevepratas.servicies.FavoriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favorite_products")
@RequiredArgsConstructor
public class FavoriteProductController {

    private final FavoriteProductService service;

    @GetMapping
    public ResponseEntity<List<FavoriteProductDTO>> allFavoritedProducts(Authentication auth) {
        return ResponseEntity.ok(service.allFavoriteProducts(auth));
    }

    @PostMapping("{productId}")
    public String favorite(@PathVariable("productId") Long productId, Authentication authentication) {
        return service.favoriteProduct(productId, authentication);
    }

    @DeleteMapping("{productId}")
    public String unfavorite(@PathVariable("productId") Long productId, Authentication auth){
        return service.unfavorite(productId, auth);
    }
}
