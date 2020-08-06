package com.dozarplati.zaim.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dozarplati.zaim.R;
import com.dozarplati.zaim.models.Credits;
import com.dozarplati.zaim.utils.App;
import com.dozarplati.zaim.utils.Eventers;

import java.util.List;



/**
 * Created by Poma on 24.11.2017.
 */

public class AdapterCredit extends RecyclerView.Adapter<AdapterCredit.ViewHolder> {

    private List<Credits> posts;

    public AdapterCredit(List<Credits> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_credit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Credits card = posts.get(position);
        if(posts.get(position).getIsActive().equals("0")){
            holder.params.height = 0;
            holder.card.setLayoutParams(holder.params);
        }
        holder.head_title.setText(card.getName());
        holder.body_text_summ.setText(String.format("%s %s %s %s %s",
                card.getSummPrefix(), card.getSummMin(), card.getSummMid(),
                card.getSummMax(), card.getSummPostfix()).trim());
        if(holder.body_text_summ.getText().toString().trim().length() == 0)
            holder.summ.setVisibility(View.GONE);

        holder.body_text_stavka.setText(String.format("%s %s %s",
                card.getPercentPrefix(), card.getPercent(), card.getPercentPostfix()));
        if(holder.body_text_stavka.getText().toString().trim().length() == 0)
            holder.stavka.setVisibility(View.GONE);

        holder.body_text_srok.setText(String.format("%s %s %s %s %s",
                card.getTermPrefix(), card.getTermMin(), card.getTermMid(),
                card.getTermMax(), card.getTermPostfix()));
        if(holder.body_text_srok.getText().toString().trim().length() == 0)
            holder.srok.setVisibility(View.GONE);

        holder.rating.setText(String.format("Рейтинг: %s из 5",Float.parseFloat(card.getScore())));
        holder.body_card_oform.setText(!card.getOrderButtonText().equals("") ? card.getOrderButtonText() : "Оформить" );

        holder.body_card_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", position);
                bundle.putString("mode", "Credit");
                bundle.putString("title", card.getName());
                Navigation.findNavController(v).navigate(R.id.navigation_zaymi_detail, bundle);
            }
        });

        holder.body_card_oform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eventers.pushOformitEventYa(String.format("%s_%s",card.getItemId(), card.getName()));
                App.urlActivity(card.getOrder(), card.getName(), card.getBrowserType(), v);
            }
        });


        Glide.with(holder.itemView).load(card.getScreen()).into(holder.head_logo);

        if(Integer.getInteger(card.getVisa(), 1) != 1  || card.getVisa().length() == 0)
            holder.visa.setVisibility(View.GONE);
        if(Integer.getInteger(card.getMastercard(), 1) != 1  || card.getMastercard().length() == 0)
            holder.mastercard.setVisibility(View.GONE);
        if(Integer.getInteger(card.getMir(), 1) != 1  || card.getMir().length() == 0)
            holder.mir.setVisibility(View.GONE);
        if(Integer.getInteger(card.getYandex(), 1) != 1  || card.getYandex().length() == 0)
            holder.ya.setVisibility(View.GONE);
        if(Integer.getInteger(card.getQiwi(), 1) != 1  || card.getQiwi().length() == 0)
            holder.qiwi.setVisibility(View.GONE);


    }


    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView head_logo, visa, ya, qiwi, mir, mastercard;
        TextView head_title, body_text_summ, body_text_stavka, body_text_srok;
        TextView rating;
        Button body_card_oform, body_card_desc;
        LinearLayout srok, summ, stavka;
        CardView card;

        final CardView.LayoutParams params;

        public ViewHolder(View itemView) {
            super(itemView);
                head_title = (TextView)itemView.findViewById(R.id.head_title);
                body_text_srok = (TextView)itemView.findViewById(R.id.body_text_srok);
                body_text_stavka = (TextView)itemView.findViewById(R.id.body_text_stavka);
                body_text_summ = (TextView)itemView.findViewById(R.id.body_text_summ);

                rating = (TextView) itemView.findViewById(R.id.rating);
                body_card_desc = (Button)itemView.findViewById(R.id.body_card_desc);
                body_card_oform = (Button)itemView.findViewById(R.id.body_card_oform);

            head_logo = (ImageView)itemView.findViewById(R.id.head_logo);
            visa = (ImageView)itemView.findViewById(R.id.visa);
            ya = (ImageView)itemView.findViewById(R.id.ya);
            qiwi = (ImageView)itemView.findViewById(R.id.qiwi);
            mir = (ImageView)itemView.findViewById(R.id.mir);
            mastercard = (ImageView)itemView.findViewById(R.id.mastercard);

            srok = (LinearLayout)itemView.findViewById(R.id.srok);
            summ = (LinearLayout)itemView.findViewById(R.id.summ);
            stavka = (LinearLayout)itemView.findViewById(R.id.stavka);
            card = (CardView)itemView.findViewById(R.id.card);

            params = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

    }
}