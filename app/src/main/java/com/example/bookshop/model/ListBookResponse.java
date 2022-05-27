package com.example.bookshop.model;

import java.util.List;

public class ListBookResponse {
    private List<BookItem> data;

    public ListBookResponse(List<BookItem> data) {
        this.data = data;
    }

    public List<BookItem> getData() {
        return data;
    }

    public void setData(List<BookItem> data) {
        this.data = data;
    }
}
