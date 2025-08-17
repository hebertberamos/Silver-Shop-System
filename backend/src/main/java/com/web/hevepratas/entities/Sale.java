package com.web.hevepratas.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_sale")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    private User customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<SaleItem> items;

    private BigDecimal saleAmount;

    private LocalDateTime saleDateTime;

    public void totalAmount(List<SaleItem> items) {
        BigDecimal totalAmount = new BigDecimal(0);

        for(SaleItem item : items) {
            BigDecimal itemPrice = item.getProduct().getProductPrice();
            BigDecimal quantity =  new BigDecimal(item.getQuantity());
            BigDecimal calcAmount = new BigDecimal(0);

            calcAmount = itemPrice.multiply(quantity);
            totalAmount = totalAmount.add(calcAmount);
            System.out.println("Total amount: " + totalAmount.toString());
        }

        this.saleAmount = totalAmount;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Sale sale = (Sale) o;
        return getId() != null && Objects.equals(getId(), sale.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
