package com.hipposupermecado;

import com.hipposupermecado.Model.Categoria;
import com.hipposupermecado.Model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Noah on 03/05/2018.
 */

public interface ApiCategoria {
    @GET("4A/webresources/categorias")
    Call<List<Categoria>> getCategorias();

    @GET("4A/webresources/categorias/{id}")
    Call<List<Produto>> getProdutos(@Path("id") int id);
}
