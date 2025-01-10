package com.web.hevepratas.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;
    @Column(name = "user_city")
    private String city;
    @Column(name = "user_address")
    private String userAddress;
    @Column(name = "user_cep")
    private String cep;
    @Column(name = "user_address_complement")
    private String complement;
    @Column(name = "user_house_number")
    private int houseNumber;
    @Column(name = "user_neighborhood")
    private String neighborhood;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Product> getShoppingCart() {
//        return shoppingCart;
//    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
