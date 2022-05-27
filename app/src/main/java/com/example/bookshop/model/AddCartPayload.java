package com.example.bookshop.model;


public class AddCartPayload {
    private String[] item_book;
    private int [] quantity;
    private String user;

    public AddCartPayload(String[] item_book, int[] quantity, String user) {
        this.item_book = item_book;
        this.quantity = quantity;
        this.user = user;
    }
}
