package com.cartapp.model.network;

import retrofit2.Retrofit;

/**
 * Created by Harshal on 24/09/17.
 */

public class CartApiCollection {

    Retrofit client;

    public static CartApiCollection instance;

    public static synchronized CartApiCollection getInstance() {
        if (instance == null) {
            instance = new CartApiCollection();
        }
        return instance;
    }

    private CartApiCollection() {
        client = ApiClient.buildClient();
    }



}
