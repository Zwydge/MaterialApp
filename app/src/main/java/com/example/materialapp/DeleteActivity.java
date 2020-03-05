package com.example.materialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.materialapp.entities.Materials;
import com.example.materialapp.entities.MaterialsResponse;
import com.example.materialapp.network.ApiService;
import com.example.materialapp.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    private static final String TAG = "DeleteActivity";

    Materials material = new Materials(1,"material_name");

    private Spinner spinner;

    ApiService service;
    Call<MaterialsResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class);

        call = service.material_get();

        spinner = findViewById(R.id.mat_list);

        List<String> materials = new ArrayList<String>();
        List<Integer> mat_id = new ArrayList<>();
        materials.add("Sélectionnez du matériel");
        mat_id.add(-1);

        call.enqueue(new Callback<MaterialsResponse>() {
            @Override
            public void onResponse(Call<MaterialsResponse> call, Response<MaterialsResponse> response) {
                Log.w(TAG, "Réponse reçue: " + response );

                if(response.isSuccessful()){
                    for (Materials e : response.body().getData()) {
                        materials.add(e.getName());
                        mat_id.add(e.getId());
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Connexion impossible", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MaterialsResponse> call, Throwable t) {
                Log.w(TAG, "Problème CAT: " + t.getMessage() );
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, materials);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                String material_selected = spinner.getSelectedItem().toString();
                material.setName(material_selected);
                material.setId(mat_id.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.go_to_dahsboard)
    void goToDashbard(){
        startActivity(new Intent(DeleteActivity.this, Dashboard.class));
    }

    @OnClick(R.id.delete_btn)
    void delete(){
        if(material.getId() != -1) {
            Log.w(TAG, "question envoyée ");
            call = service.material_delete(material.getId());
            call.enqueue(new Callback<MaterialsResponse>() {
                @Override
                public void onResponse(Call<MaterialsResponse> call, Response<MaterialsResponse> response) {
                    Log.w(TAG, "Réponse reçue askquestion: " + response);

                    if (response.isSuccessful()) {
                        Log.w(TAG, "SUCCESS");
                        Toast.makeText(getApplicationContext(), "Matériel supprimé", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DeleteActivity.this, Dashboard.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Impossible de supprimer ce matériel", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MaterialsResponse> call, Throwable t) {
                    Log.w(TAG, "Problème Delete: " + t.getMessage());
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), "Veuillez choisir un élément", Toast.LENGTH_SHORT).show();
        }

    }
}
