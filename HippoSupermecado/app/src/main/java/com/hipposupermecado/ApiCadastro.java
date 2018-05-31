package com.hipposupermecado;

import com.hipposupermecado.Model.Cadastro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiCadastro {
    @POST("/WebServiceGrupoII/webresources/cadastro/insert")
    Call<Cadastro> insertCadastro(@Body Cadastro cadastro);
}
