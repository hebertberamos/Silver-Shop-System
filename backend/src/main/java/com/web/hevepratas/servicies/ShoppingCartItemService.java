package com.web.hevepratas.servicies;

import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.ShoppingCartItem;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.repositories.ShoppingCartItemRepository;
import com.web.hevepratas.util.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemService {

    private final ShoppingCartItemRepository repository;

    private final ShoppingCartService cartService;
    private final ProductService productService;


    public String create(Authentication authentication, Long productId, int quantity) {
        User user = null;
        ShoppingCart cart = null;
        Product prod = null;
        String retResponse = "";
        ShoppingCartItem item = new ShoppingCartItem();


        try {
            user = (User) authentication.getPrincipal();
            cart = cartService.getCartByUserId(user.getId());
            prod = productService.returnProductById(productId);

            item.setCart(cart);
            item.setProduct(prod);
            item.setQuantity(quantity);

            repository.save(item);
            retResponse = "Adicionado ao carrinho com sucesso!";
        }
        catch(Exception e) {
            LogUtil.logExceptionError(e, user.getUserEmail(), "ERROR when user tries to save a new product to cart.", getClass().toString(), "Não foi possível executar a ação.");
        }

        return retResponse;
    }

    public String delete(Authentication auth, Long itemId) {
        String retResponse = "";
        User user = null;
        ShoppingCart cart = null;

        try {
            user = (User) auth.getPrincipal();
            cart = cartService.getCartByUserId(user.getId());

            for (ShoppingCartItem cartItem : cart.getItems()) {
                if (cartItem.getId() == itemId) {

                    repository.deleteById(itemId);
                    retResponse = "Removido.";

                    break;
                } else {
                    retResponse = "Item não encontrado.";
                }
            }
        } catch(Exception e) {
            LogUtil.logExceptionError(e, user.getUserEmail(), "ERROR when user tries to delete a new product to cart.", getClass().toString(), "Não foi possível executar a ação.");
        }

        return retResponse;
    }
}
