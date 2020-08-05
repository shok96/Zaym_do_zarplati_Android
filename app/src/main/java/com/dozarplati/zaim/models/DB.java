package com.dozarplati.zaim.models;

import androidx.room.Embedded;
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
        ConverterListObject.Cards_installmentsOBJ.class, ConverterListObject.Cards_debitOBJ.class,
        })
public class DB
{
    @PrimaryKey(autoGenerate = true)
    private long id;

    private List<String> news;

    private List<Cards> cards;
    @Embedded(prefix = "ru")
    private Ru ru;

    private List<String> documents;

    private List<Loans> loans;

    private List<Countries> countries;
    @Embedded(prefix = "ua")
    private Ua ua;

    private String init_license_term;
    @Embedded(prefix = "kz")
    private Kz kz;

    private List<Cards_credit> cards_credit;

    private List<Credits> credits;

    private String hideInitAgreement;

    private String license_term;

    @Embedded(prefix = "app_config")
    private App_config app_config;

    private List<Cards_installment> cards_installment;

    private List<Cards_debit> cards_debit;

    private String showDocs;

    public DB() {

    }

    public DB(long id, List<String> news, List<Cards> cards, Ru ru, List<String> documents, List<Loans> loans, List<Countries> countries, Ua ua, String init_license_term, Kz kz, List<Cards_credit> cards_credit, List<Credits> credits, String hideInitAgreement, String license_term, App_config app_config, List<Cards_installment> cards_installment, List<Cards_debit> cards_debit, String showDocs) {
        this.id = id;
        this.news = news;
        this.cards = cards;
        this.ru = ru;
        this.documents = documents;
        this.loans = loans;
        this.countries = countries;
        this.ua = ua;
        this.init_license_term = init_license_term;
        this.kz = kz;
        this.cards_credit = cards_credit;
        this.credits = credits;
        this.hideInitAgreement = hideInitAgreement;
        this.license_term = license_term;
        this.app_config = app_config;
        this.cards_installment = cards_installment;
        this.cards_debit = cards_debit;
        this.showDocs = showDocs;
    }

    public List<String> getNews ()
    {
        return news;
    }

    public void setNews (List<String> news)
    {
        this.news = news;
    }

    public List<Cards> getCards ()
    {
        return cards;
    }

    public void setCards (List<Cards> cards)
    {
        this.cards = cards;
    }

    public Ru getRu ()
    {
        return ru;
    }

    public void setRu (Ru ru)
    {
        this.ru = ru;
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

    public List<Countries> getCountries ()
    {
        return countries;
    }

    public void setCountries (List<Countries> countries)
    {
        this.countries = countries;
    }

    public Ua getUa ()
    {
        return ua;
    }

    public void setUa (Ua ua)
    {
        this.ua = ua;
    }

    public String getInit_license_term ()
    {
        return init_license_term;
    }

    public void setInit_license_term (String init_license_term)
    {
        this.init_license_term = init_license_term;
    }

    public Kz getKz ()
    {
        return kz;
    }

    public void setKz (Kz kz)
    {
        this.kz = kz;
    }

    public List<Cards_credit> getCards_credit ()
    {
        return cards_credit;
    }

    public void setCards_credit (List<Cards_credit> cards_credit)
    {
        this.cards_credit = cards_credit;
    }

    public List<Credits> getCredits ()
    {
        return credits;
    }

    public void setCredits (List<Credits> credits)
    {
        this.credits = credits;
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

    public App_config getApp_config ()
    {
        return app_config;
    }

    public void setApp_config (App_config app_config)
    {
        this.app_config = app_config;
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

    public String getShowDocs ()
    {
        return showDocs;
    }

    public void setShowDocs (String showDocs)
    {
        this.showDocs = showDocs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [news = "+news+", cards = "+cards+", ru = "+ru+", documents = "+documents+", loans = "+loans+", countries = "+countries+", ua = "+ua+", init_license_term = "+init_license_term+", kz = "+kz+", cards_credit = "+cards_credit+", credits = "+credits+", hideInitAgreement = "+hideInitAgreement+", license_term = "+license_term+", app_config = "+app_config+", cards_installment = "+cards_installment+", cards_debit = "+cards_debit+", showDocs = "+showDocs+"]";
    }
}

