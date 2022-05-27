package com.example.bookshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.LoginPayload;
import com.example.bookshop.model.LoginResponse;
import com.example.bookshop.model.RegisterPayload;
import com.example.bookshop.model.RegisterResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUsername,txtPassword,txtRePassword,txtPhone,txtEmail,txtAddress,txtName;
    private Button btnSignup;
    private TextView linkSignin;
    private CheckBox checkAgree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        initView();
        btnSignup.setOnClickListener(this);
        linkSignin.setOnClickListener(this);
    }

    private void initView() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail= findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtRePassword= findViewById(R.id.txtRePassword);
        txtName= findViewById(R.id.txtName);
        btnSignup = findViewById(R.id.btnSignup);
        linkSignin = findViewById(R.id.linkSignin);
        checkAgree = findViewById(R.id.checkAgree);
    }
    private void Signup(){
        RegisterPayload registerPayload = new RegisterPayload(txtAddress.getText().toString(),txtName.getText().toString(),txtEmail.getText().toString(),txtPhone.getText().toString(),txtUsername.getText().toString(), txtPassword.getText().toString());
        ApiService.apiService.register(registerPayload).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Toast.makeText(SignupActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                if( response.body() != null && response.body().getStatus() == true){
                    Gson gson = new Gson();
                    String user = gson.toJson(registerPayload);
                    Intent signupIntent = new Intent(SignupActivity.this,LoginActivity.class);
                    signupIntent.putExtra("registerPayload",user);
                    startActivity(signupIntent);
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this,"Signup failed!",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignup:{
                Signup();

                break;
            }
            case R.id.linkSignin:{
                Intent signupIntent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(signupIntent);
                break;
            }
        }

    }
}