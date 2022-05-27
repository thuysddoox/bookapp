package com.example.bookshop.model;

import java.io.Serializable;

public class Address implements Serializable {
    private String _id,street,district,city,no_home;

    public Address(String _id, String street, String district, String city, String no_home) {
        this._id = _id;
        this.street = street;
        this.district = district;
        this.city = city;
        this.no_home = no_home;
    }
    public Address(String street, String district, String city, String no_home) {
        this.street = street;
        this.district = district;
        this.city = city;
        this.no_home = no_home;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNo_home() {
        return no_home;
    }

    public void setNo_home(String no_home) {
        this.no_home = no_home;
    }
}
