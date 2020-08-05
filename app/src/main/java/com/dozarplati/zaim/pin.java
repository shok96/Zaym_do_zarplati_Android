package com.dozarplati.zaim;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class pin extends AppCompatActivity implements View.OnClickListener {

    Button pin_submit, pin_acept;
    EditText pin_acept_edit, pin_code, pin_phone;
    int code = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initGUI();
    }

    void initGUI(){
        pin_submit = (Button)findViewById(R.id.pin_submit);

        pin_code = (EditText) findViewById(R.id.pin_code);
        pin_code.setEnabled(false);
        pin_phone = (EditText) findViewById(R.id.pin_phone);
        pin_code.setText(String.format("+%S", TelephoneCode.getCountryDialCode(this).equals("") ? "1": TelephoneCode.getCountryDialCode(this)));
        if(pin_code.getText().toString().equals("+1")){
            pin_submit.setVisibility(View.GONE);
        }
        if(pin_code.getText().toString().equals("+7")){
            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(10);
            pin_phone.setFilters(FilterArray);
        }
        if(pin_code.getText().toString().equals("+380")){
            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(13);
            pin_phone.setFilters(FilterArray);
        }

        pin_submit.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pin_submit:
                if(pin_phone.getText().toString().length()>=10)
                    startActivity(new Intent(getApplicationContext(), pin_ac.class));
                else
                    SnackBarMessage.getMessage(v, "Введите номер теелфона");
                break;

        }
    }


}