package com.web.hevepratas.entities.enums;

public enum ProductType {

    CORD("cord"),
    BRACELET("bracelet"),
    RING("ring"),
    EARRING("earring");

    private String productType;

    ProductType(String productType){
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }
}
