package com.cartapp.model.network;


public interface DataCallback<T> {

    void onSuccess(T response);

    void onError(String errorResponse);


}
