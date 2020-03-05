package com.example.materialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.materialapp.entities.Global;
import com.example.materialapp.entities.Materials;
import com.example.materialapp.entities.Users;
import com.example.materialapp.entities.UsersResponse;
import com.example.materialapp.network.ApiService;
import com.example.materialapp.network.RetrofitBuilder;
import com.facebook.AccessToken;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    Users current_user = new Users(-1,"none", "none");

    @BindView(R.id.text_name)
    TextInputLayout temail;
    @BindView(R.id.text_password)
    TextInputLayout tpassword;

    ApiService service;
    Call<UsersResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
    }

    @OnClick(R.id.btn_login)
    void login() {

        String email = temail.getEditText().getText().toString();
        String password = tpassword.getEditText().getText().toString();

        call = service.login(email, password);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                Log.w(TAG, "onResponse: " + response);

                if (response.isSuccessful()) {
                    for (Users e : response.body().getData()) {
                        current_user.setId(e.getId());
                    }
                    Global.id = current_user.getId();
                    Toast.makeText(getApplicationContext(),"Connexion réussie",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, Dashboard.class));
                    finish();
                } else {
                    if (response.code() == 401) {
                        Toast.makeText(getApplicationContext(),"Connexion refusée",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @OnClick(R.id.go_to_dahsboard)
    void goToRegister(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
    }
}