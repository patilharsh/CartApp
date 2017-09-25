package com.cartapp.application;

import android.app.Application;

import com.cartapp.utils.AppPreferences;

public class ApplicationController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppPreferences.init(getApplicationContext());
    }
}
