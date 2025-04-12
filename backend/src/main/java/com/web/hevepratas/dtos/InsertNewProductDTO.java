package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.enums.Gender;
import com.web.hevepratas.entities.enums.ProductSubType;
import com.web.hevepratas.entities.enums.ProductType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InsertNewProductDTO {

    private String name;
    private Double price;
    private Gender gender;
    private ProductType type;
    private ProductSubType subType;
    private String description;
    private Double size;
    private Integer quantityAvailable;

}
