package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c WHERE c.product.id = :productId AND c.shoppingCart.id = :shoppingCartId")
    CartItem findByProductIdAndShoppingCartId(Long productId, Long shoppingCartId);

}
