package com.example.materialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.materialapp.entities.Global;
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

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

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
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
    }

    @OnClick(R.id.go_to_dahsboard)
    void goToRegister(){
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.btn_register)
    void register(){

        String email = temail.getEditText().getText().toString();
        String password = tpassword.getEditText().getText().toString();

        call = service.register(email, password);
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
                    startActivity(new Intent(RegisterActivity.this, Dashboard.class));
                    finish();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Connexion impossible",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null) {
            call.cancel();
            call = null;
        }
    }
}
