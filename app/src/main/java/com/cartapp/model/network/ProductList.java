package com.cartapp.model.network;

import java.util.ArrayList;

/**
 * Created by Harshal on 24/09/17.
 */

public class ProductList {
    private ArrayList<ShopApiResponse> products;

    public ArrayList<ShopApiResponse> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ShopApiResponse> products) {
        this.products = products;
    }
}
