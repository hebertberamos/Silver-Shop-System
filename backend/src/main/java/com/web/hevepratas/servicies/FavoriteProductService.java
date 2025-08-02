package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.FavoriteProductDTO;
import com.web.hevepratas.entities.FavoriteProduct;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.repositories.FavoriteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteProductService {

    private final FavoriteProductRepository repository;
    private final ProductService productService;

    public String favoriteProduct(Long productId, Authentication auth) {
        String response = null;
        User user = (User) auth.getPrincipal();
        FavoriteProduct favoriteProduct = null;

        try {
            Product product = productService.returnProductById(productId);

            if (product != null && user != null) {
                Optional<FavoriteProduct> optionalObject = repository.findByUserIdAndProductId(user.getId(), product.getId());
                if(optionalObject.isEmpty()) {
                    favoriteProduct = new FavoriteProduct(user, product);

                    repository.save(favoriteProduct);
                    response = "Favoritado!";
                }
                else {
                    response = "Produto já favoritado.";
                }
            }
        } catch (Exception e) {
            response = "Não é possível favoritar o produto.";
            System.out.println(e);
        }

        return response;
    }

    public List<FavoriteProductDTO> allFavoriteProducts(Authentication auth) {
        User user  = (User) auth.getPrincipal();
        List<FavoriteProduct> favoriteProducts = null;
        List<FavoriteProductDTO> favoriteProductsDto = new ArrayList<>();

        try{
            favoriteProducts = repository.findByUserId(user.getId());
        } catch(Exception e){
            favoriteProducts = new ArrayList<>();
        }

        for(FavoriteProduct favorite : favoriteProducts) {
            favoriteProductsDto.add(new FavoriteProductDTO(favorite));
        }

        return favoriteProductsDto;
    }

    public String unfavorite(Long productId, Authentication auth){
        User user  = (User) auth.getPrincipal();
        String response = null;

        try {
            //TODO: take off the FavoriteProduct object instantiation from try.
            FavoriteProduct favProduct = repository.findByUserIdAndProductId(user.getId(), productId).orElseThrow(() -> new ResourceNotFoundException("Não encontramos este produto na sua lista de favoritos."));

            repository.delete(favProduct);
            response = "Desfavoritado!";
            
        } catch(Exception e){
            response = "Não foi possível desfavoritar o produto...";
            System.out.println(e);
        }

        return response;
    }

}
