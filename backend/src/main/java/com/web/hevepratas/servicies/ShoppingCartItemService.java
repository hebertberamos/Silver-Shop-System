package com.web.hevepratas.servicies;

import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.ShoppingCartItem;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.repositories.ShoppingCartItemRepository;
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
        String retResponse = "";
        ShoppingCartItem item = new ShoppingCartItem();

        try {
            User user = (User) authentication.getPrincipal();
            ShoppingCart cart = cartService.getCartByUserId(user.getId());
            Product prod = productService.returnProductById(productId);

            item.setCart(cart);
            item.setProduct(prod);
            item.setQuantity(quantity);

            repository.save(item);
            retResponse = "Adicionado ao carrinho com sucesso!";
        }
        catch(Exception e) {
            retResponse = "Não foi possível adicionar o produto ao carrinho...";
            e.printStackTrace();
        }

        return retResponse;
    }

    public String delete(Authentication auth, Long itemId) {
        String retResponse = "";
        User user = (User) auth.getPrincipal();
        ShoppingCart cart = cartService.getCartByUserId(user.getId());

        for(ShoppingCartItem cartItem : cart.getItems()){
            if(cartItem.getId() == itemId) {
                try {
                    repository.deleteById(itemId);
                    retResponse = "Removido.";
                } catch(Exception e) {
                    throw new RuntimeException("Erro ao tentar remover o item do carrinho...");
                }
                    break;
            } else {
                retResponse = "Item não encontrado.";
            }
        }

        return retResponse;
    }
}
