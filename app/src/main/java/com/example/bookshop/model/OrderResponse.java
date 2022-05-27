package com.example.bookshop.model;

public class OrderResponse {
    public class Data{
        public String cart,_id,createdAt,updatedAt;
        public boolean is_payment;
        public int __v;

        public Data(String cart, String _id, String createdAt, String updatedAt, boolean is_payment, int __v) {
            this.cart = cart;
            this._id = _id;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.is_payment = is_payment;
            this.__v = __v;
        }
    }
    private String message;
    private  Data data;

    public OrderResponse(String message, Data data) {
        this.message = message;
        this.data = data;
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
