package com.example.bookshop.model;

import java.io.Serializable;

public class Author implements Serializable {
private String _id,name,biography;
    public Author(String _id, String name, String biography) {
        this._id = _id;
        this.name = name;
        this.biography = biography;
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
}
