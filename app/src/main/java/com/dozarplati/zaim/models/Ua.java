package com.dozarplati.zaim.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ua
{
    @PrimaryKey(autoGenerate = true)
    private long idb;
    private String cards_credit_item;

    private String news_item;

    private String cards_installment_item;

    private String history_item;

    private String cards_debit_item;

    private String cards_item;

    private String calculator_item;

    private String hide_order_offer;

    private String loans_item;

    private String credits_item;

    public String getCards_credit_item ()
    {
        return cards_credit_item;
    }

    public void setCards_credit_item (String cards_credit_item)
    {
        this.cards_credit_item = cards_credit_item;
    }

    public String getNews_item ()
    {
        return news_item;
    }

    public void setNews_item (String news_item)
    {
        this.news_item = news_item;
    }

    public String getCards_installment_item ()
    {
        return cards_installment_item;
    }

    public void setCards_installment_item (String cards_installment_item)
    {
        this.cards_installment_item = cards_installment_item;
    }

    public String getHistory_item ()
    {
        return history_item;
    }

    public void setHistory_item (String history_item)
    {
        this.history_item = history_item;
    }

    public String getCards_debit_item ()
    {
        return cards_debit_item;
    }

    public void setCards_debit_item (String cards_debit_item)
    {
        this.cards_debit_item = cards_debit_item;
    }

    public String getCards_item ()
    {
        return cards_item;
    }

    public void setCards_item (String cards_item)
    {
        this.cards_item = cards_item;
    }

    public String getCalculator_item ()
    {
        return calculator_item;
    }

    public void setCalculator_item (String calculator_item)
    {
        this.calculator_item = calculator_item;
    }

    public String getHide_order_offer ()
    {
        return hide_order_offer;
    }

    public void setHide_order_offer (String hide_order_offer)
    {
        this.hide_order_offer = hide_order_offer;
    }

    public String getLoans_item ()
    {
        return loans_item;
    }

    public void setLoans_item (String loans_item)
    {
        this.loans_item = loans_item;
    }

    public String getCredits_item ()
    {
        return credits_item;
    }

    public void setCredits_item (String credits_item)
    {
        this.credits_item = credits_item;
    }

    public long getIdb() {
        return idb;
    }

    public void setIdb(long idb) {
        this.idb = idb;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cards_credit_item = "+cards_credit_item+", news_item = "+news_item+", cards_installment_item = "+cards_installment_item+", history_item = "+history_item+", cards_debit_item = "+cards_debit_item+", cards_item = "+cards_item+", calculator_item = "+calculator_item+", hide_order_offer = "+hide_order_offer+", loans_item = "+loans_item+", credits_item = "+credits_item+"]";
    }
}

