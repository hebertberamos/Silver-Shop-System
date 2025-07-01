package com.web.hevepratas.entities;

import com.web.hevepratas.enums.ProductGender;
import com.web.hevepratas.enums.ProductSubType;
import com.web.hevepratas.enums.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Column(name = "product_name")
    private String productName;

    @NotBlank(message = "O preço do produto é obrigatório")
    @Column(name = "product_price" , precision = 6, scale = 2)
    private BigDecimal productPrice;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "É obrigatório definir o genero do produto")
    @Column(name = "product_gender")
    private ProductGender productGender;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "É obrigatório definir o tipo do produto")
    @Column(name = "product_type")
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "É obrigatório definir o sub-tipo do produto")
    @Column(name = "product_subtype")
    private ProductSubType productSubType;

    @NotBlank(message = "É obrigatório definir o tamanho do produto")
    @Column(name = "product_size")
    private BigDecimal productSize;

    @NotBlank(message = "Descrição obrigatória")
    @Column(name = "product_description")
    private String productDescription;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @Setter(AccessLevel.NONE)
    private List<FavoriteProduct> favoriteProducts = new ArrayList<>();
}
