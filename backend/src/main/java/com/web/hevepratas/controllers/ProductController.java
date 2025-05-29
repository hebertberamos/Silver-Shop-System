package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.servicies.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDTO body) {
        body = service.save(body);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();

        if(body != null) {
            return ResponseEntity.created(location).build();
        }

        return new ResponseEntity("Não foi possível salvar o produto. Verifique as informações e tente novamente.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<Collection<ProductDTO>> all(){
        Collection<ProductDTO> collection = service.allProducts();
        return ResponseEntity.ok(collection);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductDTO dtoBody) {
        return ResponseEntity.ok(service.update(id, dtoBody));
    }

}
 