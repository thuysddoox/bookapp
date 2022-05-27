package com.example.bookshop.model;

public class AddCartResponse {
    public class Data {
        public String [] item_book;
        public int [] quantity;
        public String user, _id,__v;
        public double total_price;
        public boolean is_order;
        public Data(String[] item_book, int[] quantity, String user, String _id, String __v, double total_price, boolean is_order) {
            this.item_book = item_book;
            this.quantity = quantity;
            this.user = user;
            this._id = _id;
            this.__v = __v;
            this.total_price = total_price;
            this.is_order = is_order;
        }

    }
    private int status;
    private String message;
    private Data data;

    public AddCartResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public AddCartResponse(int status, String message, Data data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
