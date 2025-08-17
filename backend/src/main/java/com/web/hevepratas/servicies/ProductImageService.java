package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.ProductImageDTO;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ProductImage;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.repositories.ProductImageRepository;
import com.web.hevepratas.util.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository repository;
    @Value("${product.image.path}")
    private String fileSavePath;

    public List<ProductImage> saveImages(MultipartFile mainImage, List<MultipartFile> images, Product productEntity, User user) {
        ProductImage entity = new ProductImage();
        List<ProductImage> productImages  = new ArrayList<>();


        if(mainImage.isEmpty()){
            LogUtil.logMessage(user.getUserEmail(), "User trying to save a product without image.", getClass().toString());
            throw new ResourceNotFoundException("Pelo menos uma imagem tem que ser passada como principal.");
        }

        entity = returnEntityByFile(productEntity, mainImage, true);

        productImages.add(entity);

        for(MultipartFile img : images){
            entity = returnEntityByFile(productEntity, img, false);
            productImages.add(entity);
        }

        return productImages;
    }

    public Collection<ProductImageDTO> allImagesByProductId(Long productId) throws ResourceNotFoundException{
        return repository.findByProductId(productId).stream().map(ProductImageDTO::new).collect(Collectors.toList());
    }

    public String delete(Long imageId, Authentication auth) {
        User authUser = null;

        try {
            authUser = (User) auth.getPrincipal();

            ProductImage image = repository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("Falha ao deletar imagem. Imagem não encontrada"));
            repository.delete(image);
        }
        catch(Exception e) {
            LogUtil.logExceptionError(e, authUser.getUserEmail(), "Error while trying to delete the product image " + imageId, getClass().toString(), "Não foi possível deletar esta imagem.");
        }

        return "Imagem deletada com sucesso!";
    }


    private ProductImage returnEntityByFile(Product product, MultipartFile file, boolean mainImage) {
        ProductImage entity = new ProductImage();

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String imageName = product.getId() + file.getOriginalFilename();

                Path filePath = Paths.get(fileSavePath + "/" + imageName);
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
