package com.example.materialapp.network;
import android.net.Uri;

import com.facebook.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(@Field("email") String email, @Field("password") String password);

}
