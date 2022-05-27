package com.example.bookshop.model;

import java.io.Serializable;

public class Role implements Serializable {
    private String _id,name,__v;

    public Role(String name) {
        this.name = name;
    }
}
