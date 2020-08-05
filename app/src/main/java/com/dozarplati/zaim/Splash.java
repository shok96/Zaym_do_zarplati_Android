package com.dozarplati.zaim;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dozarplati.zaim.Net.Loader;
import com.dozarplati.zaim.utils.AFURLS;
import com.dozarplati.zaim.utils.App;
import com.dozarplati.zaim.utils.FIrebaseU;
import com.dozarplati.zaim.utils.SnackBarMessage;

public class Splash extends AppCompatActivity {
    ConstraintLayout cs;
    boolean stop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cs = (ConstraintLayout)findViewById(R.id.cs);
        AFURLS.getInstance().getAF3(getIntent());
        FIrebaseU.getInstance().getInstanceId(this);

        if(App.getInstance().isNetworkConnected())
            Loader.getInstance().loadDate(cs, true, stop, getIntent().getExtras());
        else
            SnackBarMessage.getMessage(cs, "Проверьте соединение с интернетом");

    }





    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}