package com.example.bookshop.model;

public class RegisterPayload {
    private String no_home,district,street,city,fullname,email,telephone,username,password,role;
    public RegisterPayload(String no_home, String fullname, String email, String telephone, String username, String password) {
        this.no_home = no_home;
        this.district = "";
        this.street ="";
        this.city = "";
        this.fullname = fullname;
        this.email = email;
        this.telephone = telephone;
        this.username = username;
        this.password = password;
        this.role ="6275dbcda46ee8cd5761c4c3";
    }

    public String getNo_home() {
        return no_home;
    }

    public void setNo_home(String no_home) {
        this.no_home = no_home;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
