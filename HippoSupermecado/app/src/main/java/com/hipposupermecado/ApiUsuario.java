package com.hipposupermecado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Joan on 10/05/2018.
 */

public interface ApiUsuario {
    @GET("/WebServiceGrupo2/webresources/usuario/{nome},{email},{senha},{confSenha}")
    Call<String> getObject(
            @Path("nome") String endereco,
            @Path("email") String logradouro,
            @Path("senha") String numero,
            @Path("confSenha") String cep);
}
