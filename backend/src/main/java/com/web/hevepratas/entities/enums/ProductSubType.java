package com.web.hevepratas.entities.enums;

public enum ProductSubType {

    SCAPULAR("scapular"), // escapulário
    CORD("cord"), // corrente
    BRACELET("bracelet"), // pulceira bracelete
    MALLEABLE("maleable"), // Pulseira maleável
    HOOP("hoop"), // brinco argola
    LONG("long"), // Brinco longo
    STUDS("studs"), // brincos simples
    LONELY("lonely"); // Brinco solitário / ponto de luz...



    private String productSubType;

    ProductSubType(String productSubType){
        this.productSubType = productSubType;
    }

    public String getProductSubType(){
        return productSubType;
    }

}
