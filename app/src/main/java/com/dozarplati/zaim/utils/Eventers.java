package com.dozarplati.zaim.utils;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.yandex.metrica.YandexMetrica;

import java.util.HashMap;
import java.util.Map;

public class Eventers {
    private static Eventers instance;
    private static FirebaseAnalytics mFirebaseAnalytics;

    Eventers(){
        this.instance = this;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(App.getInstance().getApplicationContext());
    }

    public static Eventers getInstance() {
        return instance;
    }

    public static void pushPinEventYa(){
        Map<String, Object> eventParameters = new HashMap<String, Object>();
        eventParameters.put("value", true);
        YandexMetrica.reportEvent("pin_accepted", eventParameters);
        pushPinEventFire();
    }

    public static void pushPinEventFire(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("value", true);
        mFirebaseAnalytics.logEvent("pin_accepted", bundle);
    }


    public static void pushOformitEventYa(String offer){
        Map<String, Object> eventParameters = new HashMap<String, Object>();
        eventParameters.put(offer, true);
        YandexMetrica.reportEvent("external_link", offer);
        pushOformitEventFire();
    }

    public static void pushOformitEventFire() {
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent("external_link", bundle);
    }
}
