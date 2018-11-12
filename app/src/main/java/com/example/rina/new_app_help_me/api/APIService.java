package com.example.rina.new_app_help_me.api;

import com.example.rina.new_app_help_me.models.Result;
import com.example.rina.new_app_help_me.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface APIService {

    //The register call
    @FormUrlEncoded
    @POST("register")
    Call<com.example.rina.new_app_help_me.models.Result> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("type") String type);


    //the signin call
    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("update/{id}")
    Call<Result> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender
    );



    @GET("userby/{email}")
    Call<User> getUser(@Path("email") String email);


}
