package com.web.hevepratas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "main_image")
    private boolean mainImage;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageUrl) {
        this.imageName = imageUrl;
    }

    public boolean isMainImage() {
        return mainImage;
    }

    public void setMainImage(boolean mainImage) {
        this.mainImage = mainImage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
