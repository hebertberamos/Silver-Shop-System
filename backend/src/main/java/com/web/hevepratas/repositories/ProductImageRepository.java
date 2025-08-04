package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Collection<ProductImage> findByProductId(Long productId);

}
