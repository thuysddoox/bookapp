package com.example.bookshop.model;

import java.io.Serializable;

public class Category implements Serializable {
    private String _id,type;

    public Category(String _id, String type) {
        this._id = _id;
        this.type = type;
    }

    public Category() {
    }

    public Category(String type) {
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
