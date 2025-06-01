package com.web.hevepratas.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.naming.Name;

@Entity
@Data
@Getter
@Setter
@Table(name = "tb_product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(name = "main_image", nullable = false)
    private boolean mainImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
