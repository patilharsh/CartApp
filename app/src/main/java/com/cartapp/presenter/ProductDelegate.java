package com.cartapp.presenter;

import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;



public interface ProductDelegate {
    void consumeProducts(ArrayList<ShopApiResponse> shopApiResponses);
    void isAlredyAdded(Boolean isAdded);
}
