package com.cartapp.presenter;


import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;

public interface CartDelegate {
    void consumeCart(ArrayList<ShopApiResponse> shopApiResponses);
}
