package com.dozarplati.zaim.Net;

import com.dozarplati.zaim.db.DBReposetory;
import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.models.DateUpdate;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ServerRepo {


    public static Observable getDB() {

        return DBReposetory.getDB().subscribeOn(Schedulers.io());

    }

    public static Observable getDate() {

        return DBReposetory.getDate().subscribeOn(Schedulers.io());

    }

    public static Observable getDBFromServer(){
        return ApiCalls.getDB().doOnNext(new Consumer<DB>() {
                    @Override
                    public void accept(DB db) {
                        db.getApp_config().setFirst_run(true);
                        DBReposetory.addDB(db);
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        //DBReposetory.getDB().subscribeOn(Schedulers.io());
                    }
                }).subscribeOn(Schedulers.io());

    }


    public static Observable getDateFromServer() {
        return ApiCalls.getDate().doOnNext(new Consumer<DateUpdate>() {
                    @Override
                    public void accept(DateUpdate db) {
                        DBReposetory.deleteDate();
                        DBReposetory.addDate(db);
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        //DBReposetory.getDB().subscribeOn(Schedulers.io());
                    }
                }).subscribeOn(Schedulers.io());
    }

    public static void setAppConfig(DB app) {

       DBReposetory.updateAppConfig(app).subscribe(new DisposableObserver<Integer>() {
           @Override
           public void onNext(Integer o) {
               if(o == 0){
                   setAppConfig(app);
               }
           }

           @Override
           public void onError(Throwable e) {
                e.printStackTrace();
           }

           @Override
           public void onComplete() {

           }
       });
    }

}
