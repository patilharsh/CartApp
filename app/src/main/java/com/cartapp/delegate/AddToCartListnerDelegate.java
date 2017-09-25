package com.cartapp.delegate;

import com.cartapp.model.network.ShopApiResponse;



public interface AddToCartListnerDelegate {
    void onClick(ShopApiResponse shopApiResponse);
}
