package com.example.bookshop.model;

import java.io.Serializable;

public class Account implements Serializable {
    private User user;
    private String _id,username;
    private Role role;
    private Boolean is_active=true;

    public Account(User user, String _id, String username) {
        this.user = user;
        this._id = _id;
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}
