package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.FavoriteProductDTO;
import com.web.hevepratas.entities.FavoriteProduct;
import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.repositories.FavoriteProductRepository;
import com.web.hevepratas.util.LogUtil;
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
        User user = null;
        FavoriteProduct favoriteProduct = null;

        try {
            user = (User) auth.getPrincipal();;
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
            LogUtil.logExceptionError(e, user.getUserEmail(), "The user tried to favorite a product and didn't worked.", getClass().toString(), "Não foi possível favoritar o produto");
        }

        return response;
    }

    public List<FavoriteProductDTO> allFavoriteProducts(Authentication auth) {
        User user  = null;
        List<FavoriteProduct> favoriteProducts = null;
        List<FavoriteProductDTO> favoriteProductsDto = new ArrayList<>();

        try{
            user = (User) auth.getPrincipal();
            favoriteProducts = repository.findByUserId(user.getId());

            for(FavoriteProduct favorite : favoriteProducts) {
                favoriteProductsDto.add(new FavoriteProductDTO(favorite));
            }

        } catch(Exception e){
            LogUtil.logExceptionError(e, user.getUserEmail(), "Something went wrong when the user tried to see your oun favorite products.", getClass().toString(), "Não foi possível realizar a ação... Tente novamente mais tarde.");
        }

        return favoriteProductsDto;
    }

    public String unfavorite(Long productId, Authentication auth){
        User user  = null;
        FavoriteProduct favProduct = null;
        String response = null;

        try {
            user = (User) auth.getPrincipal();
            favProduct = repository.findByUserIdAndProductId(user.getId(), productId).orElseThrow(() -> new ResourceNotFoundException("Não encontramos este produto na sua lista de favoritos."));

            repository.delete(favProduct);
            response = "Desfavoritado!";
            
        } catch(Exception e){
            LogUtil.logExceptionError(e, user.getUserEmail(), "ERROR when trying to unfavorite a product", getClass().toString(), "Não foi possível realizar a ação... Tente novamente mais tarde.");
        }

        return response;
    }

}
