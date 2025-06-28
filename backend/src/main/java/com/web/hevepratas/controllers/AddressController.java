package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.servicies.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @GetMapping("{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PutMapping("{userId}")
    public ResponseEntity<String> update(@PathVariable("userId") Long userId, @RequestBody AddressDTO dtoBody, Authentication authentication) {
        return ResponseEntity.ok(service.update(userId, dtoBody, authentication));
    }

}
