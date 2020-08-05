package com.dozarplati.zaim.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dozarplati.zaim.db.ConverterList;

import java.util.List;



@Entity
@TypeConverters({ConverterList.class})
public class Kz
{
    @PrimaryKey(autoGenerate = true)
    private long idb;

    private List<String> news;

    private List<String> cards;

    private List<String> documents;

    private String hideInitAgreement;

    private String license_term;

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

    public List<String> getCards ()
    {
        return cards;
    }

    public void setCards (List<String> cards)
    {
        this.cards = cards;
    }

    public List<String> getDocuments ()
    {
        return documents;
    }

    public void setDocuments (List<String> documents)
    {
        this.documents = documents;
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
        return "ClassPojo [news = "+news+", cards = "+cards+", documents = "+documents+", hideInitAgreement = "+hideInitAgreement+", license_term = "+license_term+", init_license_term = "+init_license_term+", showDocs = "+showDocs+"]";
    }
}

