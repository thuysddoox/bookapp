package com.example.bookshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.adapter.BookListAdapter;
import com.example.bookshop.adapter.CartListAdapter;
import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.BookItem;
import com.example.bookshop.model.CartItemResponse;
import com.example.bookshop.model.CartResponse;
import com.example.bookshop.model.ListBookResponse;
import com.example.bookshop.model.LoginResponse;
import com.example.bookshop.model.OrderPayload;
import com.example.bookshop.model.OrderResponse;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button btnOrder;
    private TextView txtTotal;
    CartListAdapter cartListAdapter;
    private CartItemResponse currentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yourcart);
        getSupportActionBar().hide();
        initView();
        toolbar.setOnClickListener(this);
    }
    private void initView(){
        recyclerView = findViewById(R.id.rview);
        toolbar = findViewById(R.id.toolbar);
        txtTotal = findViewById(R.id.txtTotal);
        btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(this);
        cartListAdapter = new CartListAdapter();
        setListCart();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(cartListAdapter);
    }

    private void setListCart() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyRef",0);
        if (sharedPreferences.contains("isLogined") && sharedPreferences.contains("userData") && sharedPreferences.contains("access_token")){

            String token = sharedPreferences.getString("access_token","null");
            Gson gson = new Gson();
            LoginResponse loginResponse = gson.fromJson(sharedPreferences.getString("userData","null"), LoginResponse.class);
            String userId = loginResponse.getUser()._id;
            Log.e("toke",token);
            Log.e("userId",userId);
            ApiService.apiService.getListCart("Bearer "+token,userId).enqueue(new Callback<CartResponse>() {
                @Override
                public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                    Log.e("test2",Boolean.toString(response.body().getCarts().get(14).isIs_order()));

                    if (response.body() != null && response.body().getCartCurrent()!=null){
                        currentCart = response.body().getCartCurrent();
                        cartListAdapter.setList(response.body().getCartCurrent().getItem_book());
                        txtTotal.setText(Integer.toString((int)response.body().getCartCurrent().getTotal())+" đ");
                    }
                    else txtTotal.setText("0 đ");
                }
                @Override
                public void onFailure(Call<CartResponse> call, Throwable t) {
                }
            });
        }
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        setListCart();
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar:{
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnOrder:{
                if (currentCart!=null){
                   Intent intent = new Intent(CartActivity.this,OrderActivity.class);
                    Gson gson = new Gson();
                    String data = gson.toJson(currentCart);
                    intent.putExtra("cartData",data);
                    startActivity(intent);
                    break;
                }
                else  Toast.makeText(CartActivity.this,"No book in cart!",Toast.LENGTH_LONG).show();

            }
        }
    }
}