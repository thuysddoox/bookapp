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

import com.example.bookshop.adapter.CartListAdapter;
import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.BookItem;
import com.example.bookshop.model.CartItemResponse;
import com.example.bookshop.model.CartResponse;
import com.example.bookshop.model.LoginResponse;
import com.example.bookshop.model.OrderPayload;
import com.example.bookshop.model.OrderResponse;
import com.example.bookshop.model.RegisterPayload;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button btnOrder;
    private TextView txtTotal,txtAddress,txtName,txtCount;
    CartListAdapter cartListAdapter;
    private CartItemResponse currentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        getSupportActionBar().hide();
        initView();
        toolbar.setOnClickListener(this);
    }
    private void initView(){
        recyclerView = findViewById(R.id.rview);
        toolbar = findViewById(R.id.toolbar);
        txtTotal = findViewById(R.id.txtTotal);
        txtAddress = findViewById(R.id.txtAddress);
        txtName = findViewById(R.id.txtName);
        txtCount = findViewById(R.id.txtCount);
        btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(this);
        cartListAdapter = new CartListAdapter();
        Intent intent = getIntent();
        if (intent.getStringExtra("cartData") != null){
            Gson gson = new Gson();
            CartItemResponse cartData = gson.fromJson(intent.getStringExtra("cartData"),CartItemResponse.class);
            currentCart =cartData;
            cartListAdapter.setList(cartData.getItem_book());
            txtTotal.setText(Integer.toString((int)currentCart.getTotal())+" vnđ");
            txtCount.setText("Tổng số tiền ("+Integer.toString((int)currentCart.getItem_book().size())+" sản phẩm):");
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MyRef",0);
        if (sharedPreferences.contains("isLogined") && sharedPreferences.contains("userData") && sharedPreferences.contains("access_token")) {
            String token = sharedPreferences.getString("access_token", "null");
            Gson gson = new Gson();
            LoginResponse loginResponse = gson.fromJson(sharedPreferences.getString("userData", "null"), LoginResponse.class);
            txtName.setText(loginResponse.getUser().user.fullname + " | " + loginResponse.getUser().user.telephone);
            txtAddress.setText("Trần phú, Hà Đông");
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(cartListAdapter);
    }

private void createOrder(){
        if (currentCart!=null){
            ApiService.apiService.createOrder(new OrderPayload(currentCart.get_id())).enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                    if (response.body()!=null)
                        Toast.makeText(OrderActivity.this,"Order Successfully",Toast.LENGTH_LONG).show();
                    else Toast.makeText(OrderActivity.this,"Create order failed!",Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<OrderResponse> call, Throwable t) {
                    Toast.makeText(OrderActivity.this,"Create order failed!",Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(OrderActivity.this,CartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnOrder:{
                createOrder();
                Intent intent = new Intent(OrderActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}