package com.example.materialapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.materialapp.entities.Global;
import com.example.materialapp.network.ApiService;
import com.example.materialapp.network.RetrofitBuilder;
import com.facebook.AccessToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {

    private static final String TAG = "Dashboard";

    ApiService service;
    Call<AccessToken> callAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Log.w(TAG, "id utilisateur: " + Global.id );

        final Activity activity = this;
        Button load_qr = (Button) findViewById(R.id.btn_qr);
        load_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.initiateScan();
            }
        });
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.go_to_add)
    void goToAdd(){
        startActivity(new Intent(Dashboard.this, AddActivity.class));
    }

    @OnClick(R.id.go_to_delete)
    void goToDelete(){
        startActivity(new Intent(Dashboard.this, DeleteActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(getApplicationContext(), "Scan annulé", Toast.LENGTH_SHORT).show();
            }
            else{
                int material_id = Integer.parseInt(result.getContents().toString());
                callAccess = service.takes_create(Global.getId(), material_id);

                callAccess.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        Log.w(TAG, "Réponse reçue: " + response );
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Emprunt réussi", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Emprunt impossible", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        Log.w(TAG, "Problème USER: " + t.getMessage() );
                    }
                });
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
