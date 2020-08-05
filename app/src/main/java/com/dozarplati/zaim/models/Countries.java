package com.dozarplati.zaim.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Countries
{
    @PrimaryKey(autoGenerate = true)
    private long idb;
    private String code;

    private String name;

    private String icon;

    private String id;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getIcon ()
    {
        return icon;
    }

    public void setIcon (String icon)
    {
        this.icon = icon;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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
        return "ClassPojo [code = "+code+", name = "+name+", icon = "+icon+", id = "+id+"]";
    }
}

