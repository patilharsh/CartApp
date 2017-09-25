package com.cartapp.fragment;

import com.cartapp.model.network.ShopApiResponse;

/**
 * Created by Harshal on 24/09/17.
 */

public interface AddToCartListnerDelegate {
    void onClick(ShopApiResponse shopApiResponse);
}
