package com.web.hevepratas.controllers;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.servicies.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestPart("body") ProductDTO body, @RequestPart("mainImage") MultipartFile mainImage, @RequestPart("images") List<MultipartFile> images) {

        ResponseEntity<String> returnResponse = null;
 
        body = service.save(body, mainImage, images);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();

        if(body == null) {
            returnResponse = new ResponseEntity("Não foi possível salvar o produto. Verifique as informações e tente novamente.", HttpStatus.BAD_REQUEST);
        }

        returnResponse = ResponseEntity.created(location).build();

        return returnResponse;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductDTO dtoBody) {
        return ResponseEntity.ok(service.update(id, dtoBody));
    }

}
 