package com.example.bookshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.AddCartPayload;
import com.example.bookshop.model.AddCartResponse;
import com.example.bookshop.model.BookItem;
import com.example.bookshop.model.CartItemResponse;
import com.example.bookshop.model.CartResponse;
import com.example.bookshop.model.LoginPayload;
import com.example.bookshop.model.LoginResponse;
import com.example.bookshop.model.RegisterPayload;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailBookActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtTitle,txtPrice,txtCount,txtCategory,txtAuthor,txtPages,txtPublisher;
    private Button btnAdd;
    private ImageView imgBook, btnLike;
    private BookItem item;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookdetail);
        getSupportActionBar().hide();
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        btnAdd.setOnClickListener(this);
        btnLike.setOnClickListener(this);
        toolbar.setOnClickListener(this);
    }

    private void initView() {
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        txtCount = findViewById(R.id.txtCount);
        txtCategory = findViewById(R.id.txtCategory);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtPublisher = findViewById(R.id.txtPublisher);
        btnAdd = findViewById(R.id.btnAdd);
        imgBook= findViewById(R.id.imgBook);
        btnLike= findViewById(R.id.btnLike);
        toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        item = (BookItem) intent.getSerializableExtra("bookItem");
        if (item!=null){
            txtTitle.setText(item.getBook().getTitle());
            txtPrice.setText(Integer.toString((int)item.getPrice())+ " vnđ");
            txtCount.setText("Kho: " + item.getAmount());
            txtCategory.setText("Thể loại: " +item.getBook().getCategory().getType());
            txtAuthor.setText("Tác giả: "+item.getBook().getAuthor().getName());
            txtPages.setText("Số trang: "+item.getBook().getNumber_of_pages());
            txtPublisher.setText("Nhà xuất bản: "+item.getBook().getPublisher().getName());
            Picasso.get().load(item.getBook().getImage()).into(imgBook);
//            try {
//                URL newurl = new URL(item.getBook().getImage());
//                imgBook.setImageBitmap(BitmapFactory.decodeStream(newurl.openConnection().getInputStream()));
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

    }

    private void AddToCart(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyRef",0);
        if (sharedPreferences.contains("isLogined") && sharedPreferences.contains("userData") && sharedPreferences.contains("access_token")){

            String token = sharedPreferences.getString("access_token","null");
            Gson gson = new Gson();
            LoginResponse loginResponse = gson.fromJson(sharedPreferences.getString("userData","null"), LoginResponse.class);
            String userId = loginResponse.getUser()._id;
            Log.e("test",userId);
            ApiService.apiService.getListCart("Bearer "+token,userId).enqueue(new Callback<CartResponse>() {
                @Override
                public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                    CartItemResponse current = null;
                    int count = 1;

                    Log.e("test2",Boolean.toString(response.body().getCarts().get(14).isIs_order()));
                    if (response.body()!=null && response.body().getCartCurrent()!=null) {
                        current = response.body().getCartCurrent();
                        Log.e("test", response.body().getCartCurrent().toString());
                        count += current.getItem_book().size();
                    }
                    String[] item_book = new String[count];
                    int[] quantity = new int[count];

                    if(current!=null)
                       for(int i=0;i<current.getItem_book().size() ; i++) {
                            item_book[i] = current.getItem_book().get(i).get_id();
                            quantity[i] = current.getItem_book().get(i).getQuantity();
                           item_book[current.getItem_book().size()] = item.get_id();
                           quantity[current.getItem_book().size()] = 1;
                       }
                    else {
                        item_book[0] = item.get_id();
                        quantity[0] = 1;
                    }

                    Gson gson = new Gson();
                    String data = gson.toJson(new AddCartPayload(item_book,quantity,userId));
                    Log.e("res",data);
                    Log.e("res",token);
                    ApiService.apiService.addToCart("Bearer "+token,new AddCartPayload(item_book,quantity,userId)).enqueue(new Callback<AddCartResponse>() {
                        @Override
                        public void onResponse(Call<AddCartResponse> call, Response<AddCartResponse> response) {
                            Log.e("test2",response.body().getMessage());

                            if (response.body() !=null && response.body().getStatus() == 200){
                                Toast.makeText(DetailBookActivity.this,"Add book into your cart successfully!",Toast.LENGTH_LONG).show();
                                }
                            }

                        @Override
                        public void onFailure(Call<AddCartResponse> call, Throwable t) {
                            Toast.makeText(DetailBookActivity.this,"Add book into your cart failed!",Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<CartResponse> call, Throwable t) {
                    Toast.makeText(DetailBookActivity.this,"Add book into your cart Failed!",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private void likeBook(){

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:{
                AddToCart();
                break;
            }
            case R.id.btnLike:{
                likeBook();
                break;
            }
            case R.id.toolbar:{
                Intent intent = new Intent(DetailBookActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}