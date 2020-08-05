package com.dozarplati.zaim.utils;

import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarMessage {
    public static void getMessage(View ac, String s){
        Snackbar.make(ac, s, BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}
