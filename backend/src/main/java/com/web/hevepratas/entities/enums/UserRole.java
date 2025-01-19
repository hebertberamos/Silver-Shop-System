package com.web.hevepratas.entities.enums;

public enum UserRole {

    ADMIN("admin"),
    MEMBER("member"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
