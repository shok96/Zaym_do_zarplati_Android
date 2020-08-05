package com.dozarplati.zaim.db;

import android.app.Application;

import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.models.DateUpdate;
import com.dozarplati.zaim.utils.App;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;



public class DBReposetory {

    private static DBDAO dbdao = App.getDatabase().dbdao();
    private static DateDAO datedao = App.getDatabase().datedao();

    public DBReposetory(Application application) {
        DatabaseHelper db = App.getDatabase();
        dbdao = db.dbdao();
    }



//    Single<DB> saveDb(DB db){
//
//        return dbdao.getDB().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    public static Observable<DB> getDB() {
        return Observable.fromCallable(new Callable<DB>() {
            @Override
            public DB call() throws Exception {

                return dbdao.getDB();
            }
        });
    }

    public static void addDB(DB db) {
        dbdao.insert(db);
    }

    public static void deleteDB() {
        dbdao.nukeTable();
    }

    public static Observable<DateUpdate> getDate() {
        return Observable.fromCallable(new Callable<DateUpdate>() {
            @Override
            public DateUpdate call() throws Exception {

                return datedao.getDate();
            }
        });
    }

    public static void addDate(DateUpdate db) {
        datedao.insert(db);
    }

    public static void deleteDate() {
        datedao.nukeTable();
    }

    public static Observable updateAppConfig(DB app){
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return dbdao.updateAppConfig(app);
            }
        }).subscribeOn(Schedulers.io());
    }



}
