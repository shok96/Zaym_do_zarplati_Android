package com.dozarplati.zaim.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.Settings;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.facebook.FacebookSdk;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.concurrent.Callable;

import bolts.AppLinks;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.my.tracker.MyTracker.getInstanceId;


public class AFURLS {
    private static AFURLS instance;
    InstallReferrerClient referrerClient;
    String af1, af2, af3, af4, af5;
    boolean first;
    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

    AFURLS(){
        this.instance = this;
        settings = App.getInstance().getSharedPreferences("afs", Context.MODE_PRIVATE);
        editor = settings.edit();
        loadText();
        if (first) {
            saveSet("first", false);
            getAF1();
            getAF2();
            getAF4();
            getAF5();
        }
    }

    public static AFURLS getInstance() {
        return instance;
    }

    void getAF1(){
        af1 = getInstanceId(App.getInstance().getApplicationContext());
        saveSet("af1", af1);
    }

    void getAF2(){
        referrerClient = InstallReferrerClient.newBuilder(App.getInstance().getApplicationContext()).build();
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        try {
                            ReferrerDetails response = referrerClient.getInstallReferrer();
                            String referrer = response.getInstallReferrer();
                            String[] list = referrer.split("&");
                            if (referrer.contains("gclid")){
                                for(String item: list){
                                    String[] keyValue = item.split("=");
                                    if(keyValue[0].equals("gclid"))
                                        af2 = keyValue[1];
                                }
                            }
                            else{
                                af2 = "";
                                for(String item: list){
                                    String[] keyValue = item.split("=");
                                    if(keyValue[0].equals("utm_source"))
                                        af2 = keyValue[1];
                                }
                                for(String item: list){
                                    String[] keyValue = item.split("=");
                                    if(keyValue[0].equals("utm_medium"))
                                        af2 += "_"+keyValue[1];
                                }
                            }
                            saveSet("af2", af2);
                            referrerClient.endConnection();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
            }
        });
    }

    public void getAF3(Intent intent){
        FacebookSdk.sdkInitialize(App.getInstance().getApplicationContext());

        Uri targetUrl =
                AppLinks.getTargetUrlFromInboundIntent(App.getInstance().getApplicationContext(), intent);
        if (targetUrl != null) {
            String target = targetUrl.toString().replace("://", "/");
            String[] list = target.split("/");
            af3 = String.format("%s_%s", list[2], list[3]);
            if (first) {
                saveSet("af3", af3);
            }
        }
    }

    void getAF4(){
       af4 = Settings.Secure.getString(App.getInstance().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
       saveSet("af4", af4);
    }

    void getAF5(){
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return AdvertisingIdClient.getAdvertisingIdInfo(App.getInstance().getApplicationContext()).getId();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Exception {
                        return null;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        af5 = s;
                        saveSet("af5", af5);
                    }

                });
    }

    void saveSet(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    void saveSet(String key, Boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    void loadText() {
        af1 = settings.getString("af1", "");
        af2 = settings.getString("af2", "");
        af3 = settings.getString("af3", "");
        af4 = settings.getString("af4", "");
        af5 = settings.getString("af5", "");
        first = settings.getBoolean("first", true);
    }

    public String getLink(){
        loadText();
        return String.format("&aff_sub1=%s&aff_sub2=%s&aff_sub3=%s&aff_sub4=%s&aff_sub5=%s"
                ,af1, af2, af3, af4, af5);
    }

   public void saveFirst(){
        editor.putBoolean("first_app", false);
        editor.commit();
    }

   public void loadFirst() {
        App.first = settings.getBoolean("first_app", true);
    }
}
