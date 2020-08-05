package com.dozarplati.zaim.utils;

import android.util.Log;

public class logger {
    final static String TAG = "Logger_Error";
    boolean onShow = true;

    public static void log(String message){
        Log.e(TAG, message);
    }
}
