package com.example.bookshop.api;

import com.example.bookshop.model.AddCartPayload;
import com.example.bookshop.model.AddCartResponse;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.CartItemResponse;
import com.example.bookshop.model.CartResponse;
import com.example.bookshop.model.ListBookResponse;
import com.example.bookshop.model.LoginPayload;
import com.example.bookshop.model.LoginResponse;
import com.example.bookshop.model.OrderPayload;
import com.example.bookshop.model.OrderResponse;
import com.example.bookshop.model.RegisterPayload;
import com.example.bookshop.model.RegisterResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder().baseUrl("https://bookstore-api.thangld-dev.tech/api/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiService.class);

    @POST("user/register")
    Call<RegisterResponse> register(@Body RegisterPayload payload);

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginPayload payload);

    @GET("itembook")
    Call<ListBookResponse> getListBook();

    @GET("itembook")
    Call<ListBookResponse> searchBook(@Query("keysearch") String keysearch);

    @GET("book/detail")
    Call<Book> getBookDetail(@Query("book_id") String book_id);

    @GET("cart")
    Call<CartResponse> getListCart(@Header("Authorization") String token, @Query("user") String user);

    @POST("cart/add")
    Call<AddCartResponse> addToCart(@Header("Authorization") String token, @Body AddCartPayload payload);

    @POST("cart/create-order")
    Call<OrderResponse> createOrder(@Body OrderPayload payload);
}
