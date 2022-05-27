package com.example.bookshop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookshop.LoginActivity;
import com.example.bookshop.MainActivity;
import com.example.bookshop.R;
import com.example.bookshop.SignupActivity;
import com.example.bookshop.model.LoginResponse;
import com.google.gson.Gson;

public class FragmentProfile extends Fragment implements View.OnClickListener{
    private Button btnLogout;
    private TextView txtHi;
    private EditText txtUsername,txtPhone,txtEmail,txtAddress,txtName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout = view.findViewById(R.id.btnLogout);
        txtUsername = view.findViewById(R.id.editUsername);
        txtAddress = view.findViewById(R.id.editAddress);
        txtEmail= view.findViewById(R.id.editEmail);
        txtPhone = view.findViewById(R.id.editPhone);
        txtName= view.findViewById(R.id.editName);
        txtHi = view.findViewById(R.id.txtHi);

        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("MyRef", 0);
        if (sharedPreferences.contains("userData")){
            Gson gson = new Gson();
            LoginResponse loginResponse = gson.fromJson(sharedPreferences.getString("userData","null"), LoginResponse.class);
            txtUsername.setText(loginResponse.getUser().username);
            txtName.setText(loginResponse.getUser().user.fullname);
            txtPhone.setText(loginResponse.getUser().user.telephone);
            txtEmail.setText(loginResponse.getUser().user.email);
            txtHi.setText("Hi, "+loginResponse.getUser().username);
            txtAddress.setText("Hanoi");
        }

        btnLogout.setOnClickListener(this);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogout:{
                SharedPreferences sharedPreferences= getActivity().getSharedPreferences("MyRef", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(logoutIntent);
                break;
            }

        }
    }

}
