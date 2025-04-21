package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.ShoppingCart;
import com.web.hevepratas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUser(User user);

}
