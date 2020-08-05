package com.dozarplati.zaim.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dozarplati.zaim.models.DB;


@Dao
public interface DBDAO {

    @Query("SELECT * FROM DB")
    DB getDB();

    @Insert
    void insert(DB db);

    @Update
    void update(DB db);

    @Delete
    void delete(DB db);

    @Query("DELETE FROM DB")
    void nukeTable();

    @Update
    Integer updateAppConfig(DB app);
}

