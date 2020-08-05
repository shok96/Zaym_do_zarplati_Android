package com.dozarplati.zaim;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dozarplati.zaim.utils.Config;
import com.dozarplati.zaim.utils.Moderators;

public class userAcept extends AppCompatActivity {

    Button next;
    CheckBox check;
    TextView privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_acept);
        initGUI();
    }

    void initGUI(){
        next = (Button)findViewById(R.id.user_acept_next);

        privacy = (TextView)findViewById(R.id.privacy);
        privacy.setText(Html.fromHtml(String.format("<u>%s</u>", getResources().getString(R.string.privacy))));
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Privacy.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Config.getInstance().isHide_order_offer() || Moderators.getInstance().isModers())
                    startActivity(new Intent(getApplicationContext(), pin.class));
                else
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}