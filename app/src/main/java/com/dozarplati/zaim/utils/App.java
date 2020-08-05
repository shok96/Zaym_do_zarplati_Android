package com.dozarplati.zaim.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.View;

import com.dozarplati.zaim.Net.Loader;
import com.dozarplati.zaim.Net.RetrofitProvider;
import com.dozarplati.zaim.UrlActivity;
import com.dozarplati.zaim.db.DBReposetory;
import com.dozarplati.zaim.db.DatabaseHelper;
import com.my.tracker.MyTracker;
import com.my.tracker.MyTrackerParams;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class App extends Application {

    public static App instance;

    public static Activity ac;
    public static boolean first;

    private static DatabaseHelper database;
    private static DBReposetory reposetory;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = DatabaseHelper.getDatabase(this);
        setup();
    }


    void setup(){
        new DBReposetory(instance);
        new Loader();
        new Config();
        initYa();
        myTraker();
        new Eventers();
        new FIrebaseU();
        new AFURLS();
        new Moderators();
        createNetworkProvider();
    }

    public static App getInstance() {
        return instance;
    }

    public static DatabaseHelper getDatabase() {
        return database;
    }

    public static DBReposetory getReposetory(){
        reposetory = new DBReposetory(instance);
        return reposetory;
    }

    void createNetworkProvider(){
       new RetrofitProvider();
    }

    public static void urlActivity(String str, String title, String browser, View v) {
        if(!getInstance().isNetworkConnected()){
            SnackBarMessage.getMessage(v, "Проверьте соединение с интернетом");
            return;
        }
        str = str+AFURLS.getInstance().getLink();
        if(browser.equals("internal") || browser.equals(""))
        instance.getApplicationContext().startActivity(new Intent(instance.getApplicationContext(), UrlActivity.class).putExtra("title", title)
                .setAction(str).addFlags(335544320));
        else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(str)).addFlags(FLAG_ACTIVITY_NEW_TASK);
            instance.getApplicationContext().startActivity(browserIntent);
        }
    }

    void initYa() {
        String API_key = "b93f9956-075c-42da-a5fe-a406f8c38199";
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key).build();
        YandexMetrica.activate(getApplicationContext(), config);
        YandexMetrica.enableActivityAutoTracking(this);
    }

    void myTraker(){
        MyTrackerParams trackerParams = MyTracker.getTrackerParams();
        MyTracker.initTracker("68290500475696195088", this);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}

