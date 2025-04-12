package com.web.hevepratas.services;

import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ProductImage;
import com.web.hevepratas.repositories.ProductImageRepository;
import com.web.hevepratas.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

    private final String filesSavePath = "C:/Users/frsilva/Personnel/projects/hevapratas_images_repository";

    @Autowired
    private ProductImageRepository repository;

    @Autowired
    private ProductRepository productRepository;


    public ResponseEntity<String> saveImages(MultipartFile mainImage, List<MultipartFile> images, Long productId) throws Exception {

        Optional<Product> optional = productRepository.findById(productId);
        Product productEntity = optional.orElseThrow(() ->new Exception("Product with id " + productId + " not found!"));

        ProductImage entity = new ProductImage();

        List<ProductImage> productImages  = new ArrayList<>();

        if(mainImage.isEmpty()){
            throw new Exception("One main image need to be added");
        }

        entity = returnEntityByFile(productEntity, mainImage, true);

        productImages.add(entity);

        for(MultipartFile img : images){
            entity = returnEntityByFile(productEntity, img, false);
            productImages.add(entity);
        }

        for(ProductImage image : productImages){
            repository.save(image);
        }

        return ResponseEntity.ok().body("all images saved to product " + productEntity.getId());

    }


    public List<ProductImage> findByProductId(Long productId){
        return repository.findByProductId(productId);
    }

    private ProductImage returnEntityByFile(Product product, MultipartFile file, boolean mainImage){
        ProductImage entity =  new ProductImage();

        try{
            if(!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String imageName = product.getId() + file.getOriginalFilename();

                Path filePath = Paths.get(filesSavePath + "/" +  imageName);
                Files.write(filePath, bytes);

                entity.setMainImage(mainImage);
                entity.setImageName(imageName);
                entity.setProduct(product);

                return entity;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
