package com.example.bookshop.model;

import java.io.Serializable;

public class LoginResponse implements Serializable{
    public class User  {
        public String _id, fullname, telephone, email;
        public String address;
        public User(String fullname, String telephone, String email, String address) {
            this.fullname = fullname;
            this.telephone = telephone;
            this.email = email;
            this.address = address;
        }
    }
    public class Account {
        public User user;
        public String _id,username;
        public Role role;
        public Boolean is_active=true;
        public Account(User user, String _id, String username, Role role, Boolean is_active) {
            this.user = user;
            this._id = _id;
            this.username = username;
            this.role = role;
            this.is_active = is_active;
        }
    }
    private String message, access_token;
    private Account user;
    public LoginResponse(String message, String access_token, Account user) {
        this.message = message;
        this.access_token = access_token;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}
