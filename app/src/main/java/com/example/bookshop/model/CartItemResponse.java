package com.example.bookshop.model;

import java.io.Serializable;
import java.util.List;

public class CartItemResponse implements Serializable {
    private String _id;
    private boolean is_order;
    private List<BookItem> item_book;
    private double total;

    public CartItemResponse(String _id, boolean is_order, List<BookItem> item_book, double total_price) {
        this._id = _id;
        this.is_order = is_order;
        this.item_book = item_book;
        this.total = total_price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isIs_order() {
        return is_order;
    }

    public void setIs_order(boolean is_order) {
        this.is_order = is_order;
    }

    public List<BookItem> getItem_book() {
        return item_book;
    }

    public void setItem_book(List<BookItem> item_book) {
        this.item_book = item_book;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
