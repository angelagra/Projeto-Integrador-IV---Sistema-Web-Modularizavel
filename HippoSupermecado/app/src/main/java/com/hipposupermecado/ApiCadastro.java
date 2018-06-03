package com.hipposupermecado;

import com.hipposupermecado.Model.CadastroModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiCadastro {
    @POST("/WebServiceGII/webresources/cadastro/insert")
    Call<CadastroModel> insertCadastro(@Body CadastroModel cadastro);
}