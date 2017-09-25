package com.cartapp.model.network;


import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoint {
    @GET("getdata/")
    Call<ProductList> getShops();
}
