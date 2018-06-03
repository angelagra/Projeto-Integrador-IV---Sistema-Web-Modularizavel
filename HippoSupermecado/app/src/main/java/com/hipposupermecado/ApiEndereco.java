package com.hipposupermecado;

import com.hipposupermecado.Model.EnderecoModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiEndereco {
    @POST("/WebServiceGII/webresources/endereco/insert")
    Call<EnderecoModel> insertEndereco(@Body EnderecoModel endereco);

    @POST("/WebServiceGII/webresources/endereco/get")
    Call<EnderecoModel> getEndereco(@Body EnderecoModel endereco);
}