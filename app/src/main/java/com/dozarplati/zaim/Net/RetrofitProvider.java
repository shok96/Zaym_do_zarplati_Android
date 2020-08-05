package com.dozarplati.zaim.Net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    static Retrofit provider, provider_date;

    public RetrofitProvider(){
        this.provider = new Retrofit.Builder()
                .baseUrl("https://zaimbezotkaza.xyz/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.client(httpClient.build())
                .build();

        this.provider_date = new Retrofit.Builder()
                .baseUrl("https://ioszaimback.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.client(httpClient.build())
                .build();
    }

    public static Retrofit getProvider() {
        return provider;
    }

    public static Retrofit getProviderDate() {
        return provider_date;
    }
}
