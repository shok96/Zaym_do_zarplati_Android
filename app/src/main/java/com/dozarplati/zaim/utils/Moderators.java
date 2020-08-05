package com.dozarplati.zaim.utils;

import java.util.Locale;

public class Moderators {
    private static Moderators instance;
    Moderators(){
        this.instance = this;
    }

    public static Moderators getInstance() {
        return instance;
    }

    public boolean isModers(){
        return Locale.getDefault().getLanguage().toLowerCase().equals("en") ||
                !isRuUa();
    }

    public boolean isRuUa(){
        switch (TelephoneCode.getCountryDialCode(App.getInstance().getApplicationContext())){
            case "7":
                return true;
            case "380":
                return true;
            default:
                return false;
        }
    }

}
