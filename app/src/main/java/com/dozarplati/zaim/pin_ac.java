package com.dozarplati.zaim;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dozarplati.zaim.utils.Eventers;
import com.dozarplati.zaim.utils.Notif;
import com.dozarplati.zaim.utils.SnackBarMessage;
import com.dozarplati.zaim.utils.TelephoneCode;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class pin_ac extends AppCompatActivity implements View.OnClickListener {

    Button pin_submit, pin_acept;
    EditText pin_acept_edit, pin_code, pin_phone;
    TextView send;
    int code = 0;
    String coucode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_ac);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initGUI();
    }

    void initGUI(){
        pin_acept = (Button)findViewById(R.id.pin_submit_acept);
        pin_acept_edit = (EditText) findViewById(R.id.pin_acept);
        send = (TextView)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomizer();
            }
        });
        pin_acept.setOnClickListener(this);
        coucode = TelephoneCode.getCountryDialCode(this).equals("") ? "1": TelephoneCode.getCountryDialCode(this);
        randomizer();
    }

    void randomizer(){

        Observable
                .just(new Object())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new DefaultObserver<Object>() {
                    @Override
                    public void onNext(Object o) {
                        Random rand = new Random();
                        rand.setSeed(new Date().getTime());
                        code = rand.nextInt(8999) + 1000;
                        Notif.notification(String.valueOf(code), getApplicationContext());
                        pin_acept_edit.setText(String.valueOf(code));
                    }
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.pin_submit_acept:
                if(!coucode.equals("+1")) {
                    if (pin_acept_edit.getText().toString().equals(String.valueOf(code))) {

                        Eventers.pushPinEventYa();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();

                    } else
                        SnackBarMessage.getMessage(v, "Неправильный код подтверждения");
                }
                break;
        }
    }


}