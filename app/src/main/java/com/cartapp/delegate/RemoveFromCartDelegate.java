package com.cartapp.delegate;

import com.cartapp.model.network.ShopApiResponse;



public interface RemoveFromCartDelegate {
    void RemoveFromCart(ShopApiResponse shopApiResponse);
}
