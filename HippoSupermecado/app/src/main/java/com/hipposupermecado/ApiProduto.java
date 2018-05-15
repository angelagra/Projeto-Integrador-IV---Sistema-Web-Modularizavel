package com.hipposupermecado;

import com.hipposupermecado.Model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Noah on 13/05/2018.
 */

public interface ApiProduto {
    @GET("4A/webresources/detalhes/{id}")
    Call<List<Produto>> getDetalhe(@Path("id") int id);
}
