package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {

    List<FavoriteProduct> findByUserId(Long userId);


    Optional<FavoriteProduct> findByUserIdAndProductId(Long userId, Long productId);
}
