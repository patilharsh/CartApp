package com.cartapp.application;

import android.app.Application;

import com.cartapp.utils.AppPreferences;
import com.cartapp.utils.ConnectionReceiver;

public class ApplicationController extends Application {

    private static ApplicationController mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppPreferences.init(getApplicationContext());
    }

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    public void setConnectionListener(ConnectionReceiver.ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }
}
