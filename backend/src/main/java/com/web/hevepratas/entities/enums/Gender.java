package com.web.hevepratas.entities.enums;

public enum Gender {

    FEMALE("female"),
    MALE("male");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
