package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.SaleItem;
import com.web.hevepratas.entities.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {

    private Long id;
    private User customer;
    private List<SaleItem> items;
    private BigDecimal saleAmount;
    private LocalDateTime saleDateTIme;

}
