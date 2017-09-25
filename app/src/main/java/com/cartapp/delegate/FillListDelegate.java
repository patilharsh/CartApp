package com.cartapp.delegate;

import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;


public interface FillListDelegate {
    void setList(ArrayList<ShopApiResponse> shopApiResponses);
}
