package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.ProductDTO;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ProductImage;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.enums.UserRole;
import com.web.hevepratas.exceptions.InternalServerException;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.repositories.ProductRepository;
import com.web.hevepratas.servicies.configs.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;
    private final ProductImageService imageService;

    public ProductDTO save(ProductDTO dtoBody, MultipartFile mainImage, List<MultipartFile> images, Authentication auth) {
        Product productEntity = new Product();
        User authUser = (User) auth.getPrincipal();

        try {
            if(!authUser.getUserRole().equals(UserRole.ADMIN)) {
                Logger.logNotAuthorized(authUser.getUserEmail(), "Not authenticated user trying to save new product.", getClass().toString());
            }

            //TODO: Uncomment this line
//            productEntity = GlobalMapper.mapToProduct(dtoBody);

            List<ProductImage> productImages = imageService.saveImages(mainImage, images, productEntity, authUser);


            if (!productImages.isEmpty()) {
                for (ProductImage img : productImages) {
                    productEntity.getImages().add(img);
                }
            }

            productEntity = repository.save(productEntity);
        }
        catch (Exception e) {
            Logger.logExceptionError(e, authUser.getUserEmail(), "Error to save a new product", getClass().toString(), "Não foi possível salvar o produto.");
        }

        return new ProductDTO(productEntity);
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

    public String delete(Long id, Authentication auth) {
        User authUser = (User) auth.getPrincipal();

        try {
            if(!authUser.getUserRole().equals(UserRole.ADMIN)) {
                Logger.logNotAuthorized(authUser.getUserEmail(), "Not authenticated user trying to delete a product.", getClass().toString());
            }

            Product productObject = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto com id " + id + " não encontrado"));

            repository.delete(productObject);
        }
        catch (Exception e) {
            Logger.logExceptionError(e, authUser.getUserEmail(), "Error to save a new product", getClass().toString(), "Não foi possível salvar o produto.");
        }

        return "Produto deletado com sucesso!";
    }

    public ProductDTO update(Long id, ProductDTO dtoBody, Authentication auth) {
        Product productObject = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto com id " + id + " não encontrado"));
        User authUser = (User) auth.getPrincipal();

        try {
            if (productObject == null) {

                throw new InternalServerException("Não foi possível atualizar o produto.");
            }

            productObject.setProductName(dtoBody.getProductName());
            productObject.setProductPrice(dtoBody.getProductPrice());
            productObject.setProductGender(dtoBody.getProductGender());
            productObject.setProductType(dtoBody.getProductType());
            productObject.setProductSubType(dtoBody.getProductSubType());
            productObject.setProductSize(dtoBody.getProductSize());
            productObject.setProductDescription(dtoBody.getProductDescription());


            repository.save(productObject);
        }
        catch (Exception e) {

        }

        return new ProductDTO(productObject);
    }

    protected Product returnProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado..."));
    }
}
