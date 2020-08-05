package com.dozarplati.zaim.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dozarplati.zaim.db.ConverterList;
import com.dozarplati.zaim.db.ConverterListObject;

import java.util.List;


@Entity
@TypeConverters({ConverterList.class, ConverterListObject.CardsOBJ.class,
        ConverterListObject.LoansOBJ.class, ConverterListObject.CountriesOBJ.class,
        ConverterListObject.Cards_creditOBJ.class, ConverterListObject.CreditsOBJ.class,
        ConverterListObject.Cards_installmentsOBJ.class, ConverterListObject.Cards_debitOBJ.class})
public class Ru
{
    @PrimaryKey(autoGenerate = true)
    private long idb;

    private List<String> news;

    private List<Cards_credit> cards_credit;

    private List<Cards> cards;

    private List<Credits> credits;

    private List<String> documents;

    private List<Loans> loans;

    private String hideInitAgreement;

    private String license_term;

    private List<Cards_installment> cards_installment;

    private List<Cards_debit> cards_debit;

    private String init_license_term;

    private String showDocs;

    public List<String> getNews ()
    {
        return news;
    }

    public void setNews (List<String> news)
    {
        this.news = news;
    }

    public List<Cards_credit> getCards_credit ()
    {
        return cards_credit;
    }

    public void setCards_credit (List<Cards_credit> cards_credit)
    {
        this.cards_credit = cards_credit;
    }

    public List<Cards> getCards ()
    {
        return cards;
    }

    public void setCards (List<Cards> cards)
    {
        this.cards = cards;
    }

    public List<Credits> getCredits ()
    {
        return credits;
    }

    public void setCredits (List<Credits> credits)
    {
        this.credits = credits;
    }

    public List<String> getDocuments ()
    {
        return documents;
    }

    public void setDocuments (List<String> documents)
    {
        this.documents = documents;
    }

    public List<Loans> getLoans ()
    {
        return loans;
    }

    public void setLoans (List<Loans> loans)
    {
        this.loans = loans;
    }

    public String getHideInitAgreement ()
    {
        return hideInitAgreement;
    }

    public void setHideInitAgreement (String hideInitAgreement)
    {
        this.hideInitAgreement = hideInitAgreement;
    }

    public String getLicense_term ()
    {
        return license_term;
    }

    public void setLicense_term (String license_term)
    {
        this.license_term = license_term;
    }

    public List<Cards_installment> getCards_installment ()
    {
        return cards_installment;
    }

    public void setCards_installment (List<Cards_installment> cards_installment)
    {
        this.cards_installment = cards_installment;
    }

    public List<Cards_debit> getCards_debit ()
    {
        return cards_debit;
    }

    public void setCards_debit (List<Cards_debit> cards_debit)
    {
        this.cards_debit = cards_debit;
    }

    public String getInit_license_term ()
    {
        return init_license_term;
    }

    public void setInit_license_term (String init_license_term)
    {
        this.init_license_term = init_license_term;
    }

    public String getShowDocs ()
    {
        return showDocs;
    }

    public void setShowDocs (String showDocs)
    {
        this.showDocs = showDocs;
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
        return "ClassPojo [news = "+news+", cards_credit = "+cards_credit+", cards = "+cards+", credits = "+credits+", documents = "+documents+", loans = "+loans+", hideInitAgreement = "+hideInitAgreement+", license_term = "+license_term+", cards_installment = "+cards_installment+", cards_debit = "+cards_debit+", init_license_term = "+init_license_term+", showDocs = "+showDocs+"]";
    }
}

