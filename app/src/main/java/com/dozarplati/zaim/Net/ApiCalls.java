package com.dozarplati.zaim.Net;

import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.models.DateUpdate;

import io.reactivex.Observable;


public class ApiCalls {

    public static Observable<DB> getDB(){

        NetworkInteface messagesApi = RetrofitProvider.getProvider().create(NetworkInteface.class);

        return messagesApi.getDB();
        //.subscribeOn(Schedulers.io())
        //.observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<DateUpdate> getDate(){

        NetworkInteface messagesApi = RetrofitProvider.getProviderDate().create(NetworkInteface.class);

        return messagesApi.getDate();
    }

}
