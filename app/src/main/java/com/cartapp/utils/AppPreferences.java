package com.cartapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;



public class AppPreferences {

    private static AppPreferences appPreferences = new AppPreferences();

    private Context applicationContext;

    private static SharedPreferences sharedPreferences;

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
    }

    public static AppPreferences getInstance(){
        return appPreferences;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

}
