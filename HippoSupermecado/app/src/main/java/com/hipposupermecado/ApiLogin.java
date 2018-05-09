package com.hipposupermecado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Joan on 09/05/2018.
 */

public interface ApiLogin {
    @GET("/WebServiceGrupo2/webresources/Login/{login},{senha}?")
    Call<Boolean> getObject(
            @Path("login") String login,
            @Path("senha") String senha);
}
