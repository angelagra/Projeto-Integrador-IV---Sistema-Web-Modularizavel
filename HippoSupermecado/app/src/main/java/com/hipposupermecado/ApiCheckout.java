package com.hipposupermecado;

import com.hipposupermecado.Model.CheckoutModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiCheckout {
    @POST("/WebServiceGII/webresources/checkout")
    Call<CheckoutModel> insertCheckout(@Body CheckoutModel checkout);
}
