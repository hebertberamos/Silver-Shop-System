package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.ClientDTO;
import com.web.hevepratas.servicies.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody ClientDTO dtoBody) {
        service.save(dtoBody);
    }

}
