package com.example.materialapp.network;
import android.net.Uri;

import com.example.materialapp.entities.MaterialsResponse;
import com.example.materialapp.entities.Users;
import com.example.materialapp.entities.UsersResponse;
import com.facebook.AccessToken;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<UsersResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<UsersResponse> register(@Field("email") String email, @Field("password") String password);

    @POST("materials/add")
    @FormUrlEncoded
    Call<AccessToken> add(@Field("name") String name);

    @GET("materials/get")
    Call<MaterialsResponse> material_get();

    @POST("materials/delete")
    @FormUrlEncoded
    Call<MaterialsResponse> material_delete(@Field("id") int id);

    @POST("takes/create")
    @FormUrlEncoded
    Call<AccessToken> takes_create(@Field("user_id") int user_id, @Field("material_id") int material_id);

}
