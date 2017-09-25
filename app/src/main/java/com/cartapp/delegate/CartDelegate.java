package com.cartapp.delegate;


import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;

public interface CartDelegate {
    void consumeCart(ArrayList<ShopApiResponse> shopApiResponses);
}
