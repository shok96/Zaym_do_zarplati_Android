package com.dozarplati.zaim.utils;


import com.dozarplati.zaim.models.App_config;
import com.dozarplati.zaim.models.DB;

public class Config {
    private static Config instance;

    private boolean hide_order_offer;

    public boolean isHide_order_offer() {
        return hide_order_offer;
    }

    public void setHide_order_offer(boolean hide_order_offer) {
        this.hide_order_offer = hide_order_offer;
    }

    public Config(){
        instance = this;
    }

    public static Config getInstance() {
        return instance;
    }

   public void setup(DB db){
        App_config app = db.getApp_config();
        setHide_order_offer(Integer.valueOf(app.getHide_order_offer()) == 1 ? true : false);
    }
}
