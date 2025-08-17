package com.web.hevepratas.entities;

import com.web.hevepratas.enums.ProductGender;
import com.web.hevepratas.enums.ProductSubType;
import com.web.hevepratas.enums.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price" , precision = 6, scale = 2)
    private BigDecimal productPrice;

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_gender")
    private ProductGender productGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_subtype")
    private ProductSubType productSubType;

    @Column(name = "product_size")
    private BigDecimal productSize;

    @NotBlank(message = "Descrição obrigatória")
    @Column(name = "product_description")
    private String productDescription;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<FavoriteProduct> favoriteProducts = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
