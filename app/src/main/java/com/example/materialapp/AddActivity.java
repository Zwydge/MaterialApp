package com.example.materialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";

    @BindView(R.id.text_name)
    TextInputLayout tname;

    ApiService service;
    Call<AccessToken> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
    }

    @OnClick(R.id.go_to_dahsboard)
    void goToDashbard(){
        startActivity(new Intent(AddActivity.this, Dashboard.class));
    }

    @OnClick(R.id.add_mat)
    void add() {
        String name = tname.getEditText().getText().toString();

        if (!name.equals("")) {
            call = service.add(name);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                    Log.w(TAG, "onResponse: " + response);

                    if (response.isSuccessful()) {
                        Log.w(TAG, "onResponse: " + response.body());
                        startActivity(new Intent(AddActivity.this, Dashboard.class));
                        Toast.makeText(getApplicationContext(), "Matériel ajouté", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Connexion impossible", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), "Le nom ne peut pas être vide", Toast.LENGTH_SHORT).show();
        }
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
