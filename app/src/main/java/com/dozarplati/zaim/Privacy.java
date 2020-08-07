package com.dozarplati.zaim;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dozarplati.zaim.Net.Loader;


public class Privacy extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        initGUI();
    }

    void initGUI(){
        textView = (TextView)findViewById(R.id.privacy_text);
        if(Loader.getDb() != null)
            textView.setText(Html.fromHtml(Loader.getDb().getInit_license_term()));
    }
}