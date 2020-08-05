package com.dozarplati.zaim.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.dozarplati.zaim.R;


public class TelephoneCode {
    public static String getCountryDialCode(Context con){
        String contryId = null;
        String contryDialCode = "";


        TelephonyManager telephonyMngr = (TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE);

        contryId = telephonyMngr.getSimCountryIso().toUpperCase();
        String[] arrContryCode=con.getResources().getStringArray(R.array.DialingCountryCode);
        for(int i=0; i<arrContryCode.length; i++){
            String[] arrDial = arrContryCode[i].split(",");
            if(arrDial[1].trim().equals(contryId.trim())){
                contryDialCode = arrDial[0];
                break;
            }
        }
        return contryDialCode;
    }
}
