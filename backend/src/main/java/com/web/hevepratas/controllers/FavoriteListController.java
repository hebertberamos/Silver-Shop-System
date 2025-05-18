package com.web.hevepratas.controllers;

import com.web.hevepratas.services.FavoriteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite_list")
public class FavoriteListController {

    @Autowired
    private FavoriteListService service;

    @PostMapping("/{productId}")
    public ResponseEntity<?> favoriteProduct(@PathVariable Long productId) throws Exception {
        return service.favoriteProduct(productId);
    }

}
