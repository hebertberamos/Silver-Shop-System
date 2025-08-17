package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.SaleItemDTO;
import com.web.hevepratas.servicies.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @PostMapping
    public ResponseEntity<?> sale(Authentication auth, @RequestBody List<SaleItemDTO> items) {
        String returnValue = service.sale(auth, items);

        return ResponseEntity.ok(returnValue);
    }
}
