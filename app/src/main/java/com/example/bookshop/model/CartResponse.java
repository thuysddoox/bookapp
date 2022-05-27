package com.example.bookshop.model;

import java.util.List;

public class CartResponse {
    private List<CartItemResponse> carts;

    public CartResponse(List<CartItemResponse> carts) {
        this.carts = carts;
    }

    public List<CartItemResponse> getCarts() {
        return carts;
    }

    public void setCarts(List<CartItemResponse> carts) {
        this.carts = carts;
    }
    public CartItemResponse getCartCurrent(){
        CartItemResponse current = null ;
        for (int i =0;i<carts.size();i++){
            if (carts.get(i).isIs_order() == false) {current = carts.get(i); break;}
        }
        return current;
    }
}
