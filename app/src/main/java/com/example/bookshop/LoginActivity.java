package com.example.bookshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.LoginPayload;
import com.example.bookshop.model.LoginResponse;
import com.example.bookshop.model.RegisterPayload;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUsername,txtPassword;
    private Button btnSignin;
    private TextView linkSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();
        initView();
        btnSignin.setOnClickListener(this);
        linkSignup.setOnClickListener(this);


    }

    private void initView() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnSignin = findViewById(R.id.btnSignin);
        linkSignup = findViewById(R.id.linkSignup);
        Intent intent = getIntent();
        if (intent.getStringExtra("registerPayload") != null){
            Gson gson = new Gson();
            RegisterPayload user = gson.fromJson(intent.getStringExtra("registerPayload"), RegisterPayload.class);
            txtUsername.setText(user.getUsername());
            txtPassword.setText(user.getPassword());
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MyRef",0);
        if (sharedPreferences.contains("isLogined") && sharedPreferences.contains("userData") && sharedPreferences.contains("access_token")){
            Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(loginIntent);
        }
    }

    private void login(){
        LoginPayload loginPayload = new LoginPayload(txtUsername.getText().toString(), txtPassword.getText().toString());
        ApiService.apiService.login(loginPayload).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(LoginActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                if( response.body() != null){
                    Gson gson = new Gson();
                    String userData = gson.toJson(response.body());
                    SharedPreferences sharedPreferences = getSharedPreferences("MyRef",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("access_token",response.body().getAccess_token());
                    editor.putBoolean("isLogined",true);
                    editor.putString("userData", userData);
                    editor.apply();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Login failed!",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignin:{
                login();
                Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(loginIntent);
                break;
            }
            case R.id.linkSignup:{
                Intent signupIntent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(signupIntent);
                break;
            }
        }
    }
}