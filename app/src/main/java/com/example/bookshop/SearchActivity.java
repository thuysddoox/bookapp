package com.example.bookshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.adapter.BookListAdapter;
import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.BookItem;
import com.example.bookshop.model.ListBookResponse;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActivity extends AppCompatActivity implements BookListAdapter.ItemListener,View.OnClickListener {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    BookListAdapter bookListAdapter;
    SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        getSupportActionBar().hide();
        initView();
        toolbar.setOnClickListener(this);
    }
    private void initView(){
        recyclerView = findViewById(R.id.rview);
        toolbar = findViewById(R.id.toolbar2);
        bookListAdapter = new BookListAdapter();
        setListBook();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(bookListAdapter);
        bookListAdapter.setItemListener(this);
    }
    @Override
    public void onItemClick(View view, int position) {
        BookItem item = bookListAdapter.getItem(position);
        Intent intent = new Intent(SearchActivity.this, DetailBookActivity.class);
        intent.putExtra("bookItem", item);
        startActivity(intent);
    }

    private void setListBook() {
        Intent intent = getIntent();
        if (intent.getStringExtra("key") != null) {

            ApiService.apiService.searchBook(intent.getStringExtra("key")).enqueue(new Callback<ListBookResponse>() {
                @Override
                public void onResponse(Call<ListBookResponse> call, Response<ListBookResponse> response) {
                    if (response.body() != null)
                        bookListAdapter.setList(response.body().getData());
                    }

                @Override
                public void onFailure(Call<ListBookResponse> call, Throwable t) {
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setListBook();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar2:{
                Intent intent = new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}