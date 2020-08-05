package com.dozarplati.zaim.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
//@TypeConverters({DateConverter.class})
public class DateUpdate {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String date;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateConvert(){
        try {
            return new SimpleDateFormat("dd.mm.yyyy hh:mm").parse(getDate());
        } catch (ParseException e) {
            return new Date();
        }
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date = "+date+"]";
    }
}
