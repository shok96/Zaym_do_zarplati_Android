package com.dozarplati.zaim;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dozarplati.zaim.Net.Loader;


public class Info extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initGUI();
    }

    void initGUI(){
        textView = (TextView)findViewById(R.id.info_text);
        textView.setText(Html.fromHtml(Loader.getDb().getLicense_term()));
    }
}