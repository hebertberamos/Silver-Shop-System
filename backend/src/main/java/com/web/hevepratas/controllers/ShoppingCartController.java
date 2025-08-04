package com.web.hevepratas.controllers;

import com.web.hevepratas.servicies.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService service;


}
