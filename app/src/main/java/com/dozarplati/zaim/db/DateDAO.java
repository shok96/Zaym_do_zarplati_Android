package com.dozarplati.zaim.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dozarplati.zaim.models.DateUpdate;


@Dao
public interface DateDAO {

    @Query("SELECT * FROM DateUpdate")
    DateUpdate getDate();

    @Insert
    void insert(DateUpdate db);

    @Update
    void update(DateUpdate db);

    @Delete
    void delete(DateUpdate db);

    @Query("DELETE FROM DateUpdate")
    void nukeTable();
}

