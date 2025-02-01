package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.Product;
import com.web.hevepratas.entities.enums.Gender;
import com.web.hevepratas.entities.enums.ProductSubType;
import com.web.hevepratas.entities.enums.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // All products with some filters
    @Query("SELECT p FROM Product p " +
            "WHERE (:type IS NULL AND :sub_type IS NULL AND :gender IS NULL) " +
            "OR (p.gender = :gender AND (:type IS NULL OR p.type = :type) AND (:sub_type IS NULL OR p.subType = :sub_type)) " +
            "OR (p.type = :type AND (:sub_type IS NULL OR p.subType = :sub_type) AND (:gender IS NULL OR p.gender = :gender)) ")
    Page<Product> findAllProducts(@Param("type") ProductType type, @Param("sub_type") ProductSubType subType, @Param("gender") Gender gender, Pageable pageable);

}
