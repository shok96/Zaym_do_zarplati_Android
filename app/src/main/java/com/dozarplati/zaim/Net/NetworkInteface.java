package com.dozarplati.zaim.Net;

import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.models.DateUpdate;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface NetworkInteface {

    @GET("db.json")
    Observable<DB> getDB();

    @GET("date.json")
    Observable<DateUpdate> getDate();
}
