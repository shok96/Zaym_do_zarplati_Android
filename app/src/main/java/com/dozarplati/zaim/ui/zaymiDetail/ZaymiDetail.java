package com.dozarplati.zaim.ui.zaymiDetail;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.dozarplati.zaim.R;
import com.dozarplati.zaim.models.Cards_credit;
import com.dozarplati.zaim.models.Cards_debit;
import com.dozarplati.zaim.models.Cards_installment;
import com.dozarplati.zaim.models.Credits;
import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.models.Loans;
import com.dozarplati.zaim.utils.App;
import com.dozarplati.zaim.utils.Eventers;
import com.dozarplati.zaim.utils.SnackBarMessage;


public class ZaymiDetail extends Fragment {

    private ZaymiDetailViewModel mViewModel;
    Button submit;
    TextView desc, red_text, title, rating;
    ImageView logo, visa, ya, qiwi, mir, mastercard;
    String mode;
    int id;
    View root;

    public static ZaymiDetail newInstance() {
        return new ZaymiDetail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_zaymi_detail, container, false);
        initGUI(root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ZaymiDetailViewModel.class);
        // TODO: Use the ViewModel
        initEnegie();
        listnerData();
    }

    void initGUI(View root){
        desc = (TextView)root.findViewById(R.id.Zaymi_detail_desc);
        red_text = (TextView)root.findViewById(R.id.Zaymi_detail_first_pre);
        title = (TextView)root.findViewById(R.id.head_title);
        rating = (TextView)root.findViewById(R.id.rating);
        logo = (ImageView)root.findViewById(R.id.Zaymi_detail_logo);
        visa = (ImageView)root.findViewById(R.id.visa);
        mir = (ImageView)root.findViewById(R.id.mir);
        qiwi = (ImageView)root.findViewById(R.id.qiwi);
        ya = (ImageView)root.findViewById(R.id.ya);
        mastercard = (ImageView)root.findViewById(R.id.mastercard);
        submit = (Button)root.findViewById(R.id.pin_submit_acept2);
    }

    void initEnegie(){
        id = getArguments().getInt("id");
        mode = getArguments().getString("mode");
    }

    void listnerData(){

        mViewModel.getDB().observe(getViewLifecycleOwner(), new Observer<DB>() {
            @Override
            public void onChanged(DB db) {
                if(db != null)
                    setupGUI(db);
                else
                    {
                        root.findViewById(R.id.scroll).setVisibility(View.GONE);
                        root.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
                    }
            }
        });

    }

    void setupGUI(DB db){
        switch (mode){
            case "zaym":
                loans(db);
            break;
            case "cardCredit":
                cardCredit(db);
                break;
            case "cardDebet":
                cardDebet(db);
                break;
            case "cardInstallment":
                cardInstallment(db);
                break;
            case "Credit":
                credit(db);
                break;
        }

    }

    void loans(DB db){
        Loans loan = db.getLoans().get(id);
        submit.setText(!loan.getOrderButtonText().equals("") ? loan.getOrderButtonText() : "Оформить" );
        desc.setText(Html.fromHtml(loan.getDescription()));
        red_text.setText(loan.getRedStickerText());
        if(loan.getRedStickerText().length() == 0)
            red_text.setVisibility(View.GONE);
        Glide.with(this).load(loan.getScreen()).into(logo);
        title.setText(loan.getName());
        rating.setText(String.format("Рейтинг: %s из 5",Float.parseFloat(loan.getScore())));


        if(Integer.getInteger(loan.getVisa(), 1) != 1  || loan.getVisa().length() == 0)
            visa.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getYandex(), 1) != 1  || loan.getYandex().length() == 0)
            ya.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getQiwi(), 1) != 1  || loan.getQiwi().length() == 0)
            qiwi.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMastercard(), 1) != 1  || loan.getMastercard().length() == 0)
            mastercard.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMir(), 1) != 1  || loan.getMir().length() == 0)
            mir.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eventers.pushOformitEventYa(String.format("%s_%s",loan.getItemId(), loan.getName()));
                App.urlActivity(loan.getOrder(), loan.getName(), loan.getBrowserType(), v);
            }
        });
    }

    void cardCredit(DB db){
        Cards_credit loan = db.getCards_credit().get(id);
        submit.setText(!loan.getOrderButtonText().equals("") ? loan.getOrderButtonText() : "Оформить" );
        desc.setText(Html.fromHtml(loan.getDescription()));
        red_text.setText(loan.getRedStickerText());
        if(loan.getRedStickerText().length() == 0)
            red_text.setVisibility(View.GONE);
        Glide.with(this).load(loan.getScreen()).into(logo);
        title.setText(loan.getName());
        rating.setText(String.format("Рейтинг: %s из 5",Float.parseFloat(loan.getScore())));

        if(Integer.getInteger(loan.getVisa(), 1) != 1  || loan.getVisa().length() == 0)
            visa.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getYandex(), 1) != 1  || loan.getYandex().length() == 0)
            ya.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getQiwi(), 1) != 1  || loan.getQiwi().length() == 0)
            qiwi.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMastercard(), 1) != 1  || loan.getMastercard().length() == 0)
            mastercard.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMir(), 1) != 1  || loan.getMir().length() == 0)
            mir.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eventers.pushOformitEventYa(String.format("%s_%s",loan.getItemId(), loan.getName()));
                App.urlActivity(loan.getOrder(), loan.getName(), loan.getBrowserType(), v);
            }
        });
    }

    void cardDebet(DB db){
        Cards_debit loan = db.getCards_debit().get(id);
        submit.setText(!loan.getOrderButtonText().equals("") ? loan.getOrderButtonText() : "Оформить" );
        desc.setText(Html.fromHtml(loan.getDescription()));
        red_text.setText(loan.getRedStickerText());
        if(loan.getRedStickerText().length() == 0)
            red_text.setVisibility(View.GONE);
        Glide.with(this).load(loan.getScreen()).into(logo);
        title.setText(loan.getName());
        rating.setText(String.format("Рейтинг: %s из 5",Float.parseFloat(loan.getScore())));

        if(Integer.getInteger(loan.getVisa(), 1) != 1  || loan.getVisa().length() == 0)
            visa.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getYandex(), 1) != 1  || loan.getYandex().length() == 0)
            ya.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getQiwi(), 1) != 1  || loan.getQiwi().length() == 0)
            qiwi.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMastercard(), 1) != 1  || loan.getMastercard().length() == 0)
            mastercard.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMir(), 1) != 1  || loan.getMir().length() == 0)
            mir.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eventers.pushOformitEventYa(String.format("%s_%s",loan.getItemId(), loan.getName()));
                App.urlActivity(loan.getOrder(), loan.getName(), loan.getBrowserType(), v);
            }
        });
    }

    void cardInstallment(DB db){
        Cards_installment loan = db.getCards_installment().get(id);
        submit.setText(!loan.getOrderButtonText().equals("") ? loan.getOrderButtonText() : "Оформить" );
        desc.setText(Html.fromHtml(loan.getDescription()));
        red_text.setText(loan.getRedStickerText());
        if(loan.getRedStickerText().length() == 0)
            red_text.setVisibility(View.GONE);
        Glide.with(this).load(loan.getScreen()).into(logo);
        title.setText(loan.getName());
        rating.setText(String.format("Рейтинг: %s из 5",Float.parseFloat(loan.getScore())));

        if(Integer.getInteger(loan.getVisa(), 1) != 1  || loan.getVisa().length() == 0)
            visa.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getYandex(), 1) != 1  || loan.getYandex().length() == 0)
            ya.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getQiwi(), 1) != 1  || loan.getQiwi().length() == 0)
            qiwi.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMastercard(), 1) != 1  || loan.getMastercard().length() == 0)
            mastercard.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMir(), 1) != 1  || loan.getMir().length() == 0)
            mir.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eventers.pushOformitEventYa(String.format("%s_%s",loan.getItemId(), loan.getName()));
                App.urlActivity(loan.getOrder(), loan.getName(), loan.getBrowserType(), v);
            }
        });
    }

    void credit(DB db){
        Credits loan = db.getCredits().get(id);
        submit.setText(!loan.getOrderButtonText().equals("") ? loan.getOrderButtonText() : "Оформить" );
        desc.setText(Html.fromHtml(loan.getDescription()));
        red_text.setText(loan.getRedStickerText());
        if(loan.getRedStickerText().length() == 0)
            red_text.setVisibility(View.GONE);
        //Glide.with(this).load(loan.getScreen()).into(logo);
        logo.setVisibility(View.GONE);
        title.setText(loan.getName());
        rating.setText(String.format("Рейтинг: %s из 5",Float.parseFloat(loan.getScore())));

        if(Integer.getInteger(loan.getVisa(), 1) != 1  || loan.getVisa().length() == 0)
            visa.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getYandex(), 1) != 1  || loan.getYandex().length() == 0)
            ya.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getQiwi(), 1) != 1  || loan.getQiwi().length() == 0)
            qiwi.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMastercard(), 1) != 1  || loan.getMastercard().length() == 0)
            mastercard.setVisibility(View.GONE);
        if(Integer.getInteger(loan.getMir(), 1) != 1  || loan.getMir().length() == 0)
            mir.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eventers.pushOformitEventYa(String.format("%s_%s",loan.getItemId(), loan.getName()));
                App.urlActivity(loan.getOrder(), loan.getName(), loan.getBrowserType(), v);
            }
        });
    }


}