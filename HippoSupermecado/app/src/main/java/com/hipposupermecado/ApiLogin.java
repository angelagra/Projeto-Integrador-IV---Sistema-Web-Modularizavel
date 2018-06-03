package com.hipposupermecado;

import com.hipposupermecado.Model.LoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiLogin {
    @POST("/WebServiceGII/webresources/login")
    Call<LoginModel> getLogin(@Body LoginModel login);
}