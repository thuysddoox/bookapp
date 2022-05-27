package com.example.bookshop.model;

public class LoginPayload {
    private String username, password;

    public LoginPayload(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
