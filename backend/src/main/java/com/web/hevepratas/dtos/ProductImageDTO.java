package com.web.hevepratas.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductImageDTO {
    private Long id;
    private String imageName;
    private boolean mainImage;

}
