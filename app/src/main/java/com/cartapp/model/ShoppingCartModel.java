package com.cartapp.model;


import android.content.Intent;
import android.net.Uri;

import com.cartapp.fragment.RemoveNotifyDelegate;
import com.cartapp.model.network.ApiClient;
import com.cartapp.model.network.EndPoint;
import com.cartapp.model.network.ProductList;
import com.cartapp.model.network.ShopApiResponse;
import com.cartapp.presenter.CartDelegate;
import com.cartapp.presenter.ProductDelegate;
import com.cartapp.utils.AppPreferences;
import com.cartapp.utils.ObjectSerializer;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cartapp.utils.AppConstants.CART_LIST;


public class ShoppingCartModel {

    private EndPoint apiService = null;
    private Call<ProductList> call = null;
    private ArrayList<ShopApiResponse> cartshopApiResponses;

    private static ShoppingCartModel shoppingCartModel = null;

    private ShoppingCartModel() {
        apiService = ApiClient.buildClient().create(EndPoint.class);
        call = apiService.getShops();
    }

    public static ShoppingCartModel getInstance() {
        if (shoppingCartModel == null) {
            shoppingCartModel = new ShoppingCartModel();
        }
        return shoppingCartModel;
    }


    public void getProducts(final ProductDelegate productDelegate) {


        call.clone().enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {

                ArrayList<ShopApiResponse> shopApiResponses = response.body().getProducts();
                productDelegate.consumeProducts(shopApiResponses);

            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {

            }
        });


    }


    public void getCart(CartDelegate cartDelegate) {
        if (null == cartshopApiResponses) {
            cartshopApiResponses = new ArrayList<ShopApiResponse>();
        }
        try {
            cartshopApiResponses = (ArrayList<ShopApiResponse>) ObjectSerializer.deserialize(AppPreferences.getInstance().getString(CART_LIST, ObjectSerializer.serialize(new ArrayList<ShopApiResponse>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cartDelegate.consumeCart(cartshopApiResponses);

    }

    public void addToCart(ShopApiResponse shopApiResponse, ProductDelegate productDelegate) {

        if (!AlreadyInCart(shopApiResponse)) {
            if (null == cartshopApiResponses) {
                cartshopApiResponses = new ArrayList<ShopApiResponse>();
            }
            cartshopApiResponses.add(shopApiResponse);

            try {
                AppPreferences.getInstance().putString(CART_LIST, ObjectSerializer.serialize(cartshopApiResponses));
            } catch (IOException e) {
                e.printStackTrace();
            }
            productDelegate.isAlredyAdded(true);
        }else {
            productDelegate.isAlredyAdded(false);
        }
    }

    public boolean AlreadyInCart(ShopApiResponse shopApiResponse) {

        if (null == cartshopApiResponses) {
            cartshopApiResponses = new ArrayList<ShopApiResponse>();
        }
        try {
            cartshopApiResponses = (ArrayList<ShopApiResponse>) ObjectSerializer.deserialize(AppPreferences.getInstance().getString(CART_LIST, ObjectSerializer.serialize(new ArrayList<ShopApiResponse>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cartshopApiResponses.contains(shopApiResponse)) {
            return true;
        } else {
            return false;
        }
    }


    public void RemoveFromCart(ShopApiResponse shopApiResponse, RemoveNotifyDelegate removeNotifyDelegate){

        if (null == cartshopApiResponses) {
            cartshopApiResponses = new ArrayList<ShopApiResponse>();
        }
        try {
            cartshopApiResponses = (ArrayList<ShopApiResponse>) ObjectSerializer.deserialize(AppPreferences.getInstance().getString(CART_LIST, ObjectSerializer.serialize(new ArrayList<ShopApiResponse>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cartshopApiResponses.contains(shopApiResponse)) {
            int indexOfItem = cartshopApiResponses.indexOf(shopApiResponse);
            cartshopApiResponses.remove(shopApiResponse);

            try {
                AppPreferences.getInstance().putString(CART_LIST, ObjectSerializer.serialize(cartshopApiResponses));
            } catch (IOException e) {
                e.printStackTrace();
            }

            removeNotifyDelegate.RemoveStatus(true,indexOfItem );
        } else {
            removeNotifyDelegate.RemoveStatus(false, -1);
        }

    }

    public int GetTotalAmount(){

        int result = 0;
        if (null == cartshopApiResponses) {
            cartshopApiResponses = new ArrayList<ShopApiResponse>();
        }
        try {
            cartshopApiResponses = (ArrayList<ShopApiResponse>) ObjectSerializer.deserialize(AppPreferences.getInstance().getString(CART_LIST, ObjectSerializer.serialize(new ArrayList<ShopApiResponse>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cartshopApiResponses.size()>0){

            for (ShopApiResponse shopApiResponse: cartshopApiResponses){
                result = result+ Integer.parseInt(shopApiResponse.getPrice());

            }

            return result;
        }else {

            return result;
        }

    }


}
