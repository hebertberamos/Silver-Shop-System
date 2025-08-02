package com.web.hevepratas.controllers;

import com.web.hevepratas.servicies.ShoppingCartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartItem")
@RequiredArgsConstructor
public class ShoppingCartItemController {

    private final ShoppingCartItemService service;

    @PostMapping("/{productId}/{quantity}")
    public ResponseEntity<?> addProduct(Authentication auth, @PathVariable("productId") Long productId, @PathVariable("quantity") int quantity){
        return ResponseEntity.ok(service.create(auth, productId, quantity));
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<String> removeItem(Authentication auth, @PathVariable("itemId") Long itemId){
        return ResponseEntity.ok(service.delete(auth, itemId));
    }

}
