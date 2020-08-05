package com.dozarplati.zaim.utils;

import android.widget.Toast;

public class ToastMessage {

    public static void getMessage(String s){
        Toast.makeText(App.getInstance().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

}
