package com.cartapp.presenter;


import com.cartapp.delegate.CartDelegate;
import com.cartapp.delegate.FillListDelegate;
import com.cartapp.delegate.RemoveNotifyDelegate;
import com.cartapp.model.ShoppingCartModel;
import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;

public class CartPresenter implements CartDelegate {

    private FillListDelegate fillListDelegate;
    private RemoveNotifyDelegate removeNotifyDelegate;

    public CartPresenter(FillListDelegate fillListDelegate, RemoveNotifyDelegate removeNotifyDelegate) {
        this.fillListDelegate = fillListDelegate;
        this.removeNotifyDelegate=removeNotifyDelegate;
    }
    @Override
    public void consumeCart(ArrayList<ShopApiResponse> shopApiResponses) {
        fillListDelegate.setList(shopApiResponses);
    }


    public void getCart(){
        ShoppingCartModel.getInstance().getCart(this);
    }

    public void RemoveFromCart(ShopApiResponse shopApiResponse){
        ShoppingCartModel.getInstance().RemoveFromCart(shopApiResponse,removeNotifyDelegate);
    }

    public int TotalAmount(){
        return ShoppingCartModel.getInstance().GetTotalAmount();
    }

}
