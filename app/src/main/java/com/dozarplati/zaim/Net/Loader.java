package com.dozarplati.zaim.Net;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dozarplati.zaim.MainActivity;
import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.models.DateUpdate;
import com.dozarplati.zaim.pin;
import com.dozarplati.zaim.userAcept;
import com.dozarplati.zaim.utils.AFURLS;
import com.dozarplati.zaim.utils.App;
import com.dozarplati.zaim.utils.Config;
import com.dozarplati.zaim.utils.Moderators;
import com.dozarplati.zaim.utils.SnackBarMessage;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;


import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.dozarplati.zaim.utils.logger.log;


public class Loader {
    private static Loader instance;
    private static DB dbGlobal;

    public Loader(){
        instance = this;
    }

    public static Loader getInstance() {
        return instance;
    }

    public static DB getDb() {
        if(dbGlobal != null)
            return dbGlobal;
        else
        {
            dbGlobal = null;
            ServerRepo.getDB().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DB>() {
                @Override
                public void onNext(DB o) {
                    dbGlobal = o;
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
            return dbGlobal;
        }
    }

    private void next(DB o, boolean first, boolean stop, Bundle bundle){
        dbGlobal = o;
        Config.getInstance().setup(o);
        log("data is loading");
        if(bundle != null) {

            Intent intent = new Intent(App.getInstance().getApplicationContext(), MainActivity.class);
            log(bundle.getString("cat", "err") + " " + bundle.getInt("pos", 0));
            switch (bundle.getString("cat", "err")){
                case "loans":
                    stop = true;
                    intent.putExtra("cat", "zaym");
                    intent.putExtra("pos", bundle.getInt("pos", 0));
                    App.getInstance().getApplicationContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                    break;
                case "cards_credit":
                    stop = true;
                    intent.putExtra("cat", "cardCredit");
                    intent.putExtra("pos", bundle.getInt("pos", 0));
                    App.getInstance().getApplicationContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                    break;
                case "cards_debit":
                    stop = true;
                    intent.putExtra("cat", "cardDebet");
                    intent.putExtra("pos", bundle.getInt("pos", 0));
                    App.getInstance().getApplicationContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                    break;
                case "cards_installment":
                    stop = true;
                    intent.putExtra("cat", "cardInstallment");
                    intent.putExtra("pos", bundle.getInt("pos", 0));
                    App.getInstance().getApplicationContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                    break;
                case "credits":
                    stop = true;
                    intent.putExtra("cat", "Credit");
                    intent.putExtra("pos", bundle.getInt("pos", 0));
                    App.getInstance().getApplicationContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                    break;
            }


        }
        if(stop)
            return;


        if(first) {
            AFURLS.getInstance().loadFirst();

            //if(dbGlobal.getApp_config().isFirst_run()) {
            if(App.first){
                if (dbGlobal.getHideInitAgreement().equals("0") || Moderators.getInstance().isModers())
                    App.getInstance().startActivity(new Intent(App.getInstance().getApplicationContext(), userAcept.class).addFlags(FLAG_ACTIVITY_NEW_TASK));
                else {
                    if (dbGlobal.getApp_config().getHide_order_offer().equals("1") || Moderators.getInstance().isModers())
                        App.getInstance().startActivity(new Intent(App.getInstance().getApplicationContext(), pin.class).addFlags(FLAG_ACTIVITY_NEW_TASK));
                    else
                        App.getInstance().startActivity(new Intent(App.getInstance().getApplicationContext(), MainActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK));
                }
            }
                else
                App.getInstance().startActivity(new Intent(App.getInstance().getApplicationContext(), MainActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK));
        }
    }

    public void loadDate(View ac, boolean first, boolean stop, Bundle bundle){

        SnackBarMessage.getMessage(ac, "load data...");
        ServerRepo.getDate().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DateUpdate>() {
            @Override
            public void onNext(DateUpdate db) {
                log(db.toString());
                if(db.getDateConvert().before(new Date())){
                    ServerRepo.getDB().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DB>() {
                        @Override
                        public void onNext(DB o) {
                            SnackBarMessage.getMessage(ac, "data is loading1");
                            log("data is loading1");
                            next(o, first, stop, bundle);
                        }

                        @Override
                        public void onError(Throwable e) {
                            ServerRepo.getDBFromServer().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DB>() {
                                @Override
                                public void onNext(DB o) {
                                        SnackBarMessage.getMessage(ac, "data is loading2");
                                    log("data is loading2");
                                       next(o, first, stop, bundle);
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

                        @Override
                        public void onComplete() {

                        }
                    });
                }
                else
                {
                    ServerRepo.getDateFromServer().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DateUpdate>() {
                        @Override
                        public void onNext(DateUpdate o) {
                            log(o.toString());
                            ServerRepo.getDBFromServer().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DB>() {
                                @Override
                                public void onNext(DB o) {
                                    SnackBarMessage.getMessage(ac, "data is loading3");
                                    log("data is loading3");
                                    next(o, first, stop, bundle);
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

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ServerRepo.getDateFromServer().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DateUpdate>() {
                    @Override
                    public void onNext(DateUpdate o) {
                        log(o.toString());
                        ServerRepo.getDBFromServer().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DB>() {
                            @Override
                            public void onNext(DB o) {
                                SnackBarMessage.getMessage(ac, "data is loading4");
                                log("data is loading4");
                                next(o, first, stop, bundle);
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

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
