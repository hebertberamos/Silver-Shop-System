package com.web.hevepratas.services;

import com.web.hevepratas.entities.ProductImage;
import com.web.hevepratas.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository repository;


    public void saveImages(List<ProductImage> images){
        for(ProductImage image : images){
            repository.save(image);
            System.out.println("Image " + image.getProduct() + " save successfully to the product " + image.getProduct().getName() + " id " + image.getProduct().getId());
        }

    }

}
