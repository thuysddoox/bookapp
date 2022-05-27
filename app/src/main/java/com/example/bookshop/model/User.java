package com.example.bookshop.model;

import java.io.Serializable;

public class User implements Serializable {
    private String _id, fullname, telephone, email;
    private Address address;
    public User(String _id, String fullname, String telephone, String email, Address address) {
        this._id = _id;
        this.fullname = fullname;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public User(String fullname, String telephone, String email, Address address) {
        this.fullname = fullname;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
