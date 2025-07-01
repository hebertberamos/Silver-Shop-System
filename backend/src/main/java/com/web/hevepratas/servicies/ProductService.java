package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ProductImage;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.mappers.GlobalMapper;
import com.web.hevepratas.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductImageService imageService;

    public ProductDTO save(ProductDTO dtoBody, MultipartFile mainImage, List<MultipartFile> images) {
        Product productEntity = new Product();
        productEntity = GlobalMapper.mapToProduct(dtoBody);

        List<ProductImage> productImages = imageService.saveImages(mainImage, images, productEntity);


        if(!productImages.isEmpty()) {
            for(ProductImage img : productImages) {
                productEntity.getImages().add(img);
            }
        }

        return new ProductDTO(repository.save(productEntity));
    }

    public Collection<ProductDTO> allProducts() {
        Collection<Product> products = repository.findAll();
        Collection<ProductDTO> productsDto = new ArrayList<>();

        for(Product product : products) {
            productsDto.add(new ProductDTO(product));
        }

        return productsDto;
    }

    public ProductDTO findById(Long id) {
        Optional<Product> optionalObject = repository.findById(id);
        return  new ProductDTO(optionalObject.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado")));
    }

    public String delete(Long id) {
        Product productObject = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto com id " + id + " não encontrado"));

        repository.delete(productObject);

        return "Produto deletado com sucesso!";
    }

    public ProductDTO update(Long id, ProductDTO dtoBody) {
        Product productObject = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto com id " + id + " não encontrado"));

        if(productObject == null){
            throw new RuntimeException("O usuário com id " + id + " foi encontrado, mas algo deu errado. Ele veio como null.");
        }

        productObject.setProductName(dtoBody.getProductName());
        productObject.setProductPrice(dtoBody.getProductPrice());
        productObject.setProductGender(dtoBody.getProductGender());
        productObject.setProductType(dtoBody.getProductType());
        productObject.setProductSubType(dtoBody.getProductSubType());
        productObject.setProductSize(dtoBody.getProductSize());
        productObject.setProductDescription(dtoBody.getProductDescription());


        repository.save(productObject);

        return new ProductDTO(productObject);
    }

    protected Product returnProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado..."));
    }

}
