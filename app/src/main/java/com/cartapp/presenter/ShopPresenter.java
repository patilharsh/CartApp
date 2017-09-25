package com.cartapp.presenter;


import com.cartapp.delegate.AlreadyAdddedDelegate;
import com.cartapp.delegate.FillListDelegate;
import com.cartapp.delegate.ProductDelegate;
import com.cartapp.model.ShoppingCartModel;
import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;

public class ShopPresenter implements ProductDelegate {

    private FillListDelegate fillListDelegate;
    private AlreadyAdddedDelegate alreadyAdddedDelegate;

    public ShopPresenter(FillListDelegate fillListDelegate, AlreadyAdddedDelegate alreadyAdddedDelegate) {
        this.fillListDelegate = fillListDelegate;
        this.alreadyAdddedDelegate=alreadyAdddedDelegate;
    }

    @Override
    public void consumeProducts(ArrayList<ShopApiResponse> shopApiResponses) {
        fillListDelegate.setList(shopApiResponses);
    }

    @Override
    public void isAlredyAdded(Boolean isAdded) {
        alreadyAdddedDelegate.getStatusAdded(isAdded);
    }


    public void getProducts() {
        ShoppingCartModel.getInstance().getProducts(this);
    }

    public void addCart(ShopApiResponse shopApiResponse){
        ShoppingCartModel.getInstance().addToCart(shopApiResponse,this);
    }

}
