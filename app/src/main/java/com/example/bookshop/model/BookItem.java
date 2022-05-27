package com.example.bookshop.model;

import java.io.Serializable;

public class BookItem implements Serializable {
    private String  _id;
    private double price;
    private int amount,quantity;
    private Book book;

    public BookItem(String _id, double price, int amount, Book book) {
        this._id = _id;
        this.price = price;
        this.amount = amount;
        this.book = book;
    }

    public BookItem(String _id, double price, int amount, int quantity, Book book) {
        this._id = _id;
        this.price = price;
        this.amount = amount;
        this.quantity = quantity;
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
