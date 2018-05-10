package com.hipposupermecado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Joan on 10/05/2018.
 */

public interface ApiEndereco {
    @GET("/WebServiceGrupo2/webresources/endereco/{endereco},{logradouro},{numero},{cep},{complemento},{cidade},{pais},{uf}")
    Call<String> getObject(
            @Path("endereco") String endereco,
            @Path("logradouro") String logradouro,
            @Path("numero") String numero,
            @Path("cep") String cep,
            @Path("complemento") String complemento,
            @Path("cidade") String cidade,
            @Path("pais") String pais,
            @Path("uf") String uf);
}
