package com.dozarplati.zaim.db;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dozarplati.zaim.models.App_config;
import com.dozarplati.zaim.models.Cards;
import com.dozarplati.zaim.models.Cards_credit;
import com.dozarplati.zaim.models.Cards_debit;
import com.dozarplati.zaim.models.Cards_installment;
import com.dozarplati.zaim.models.Countries;
import com.dozarplati.zaim.models.Credits;
import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.models.DateUpdate;
import com.dozarplati.zaim.models.Kz;
import com.dozarplati.zaim.models.Loans;
import com.dozarplati.zaim.models.Ru;
import com.dozarplati.zaim.models.Ua;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {DB.class, App_config.class, Cards.class, Cards_credit.class,
        Cards_debit.class, Cards_installment.class, Countries.class, Credits.class,
        Kz.class, Loans.class, Ru.class, Ua.class, DateUpdate.class}, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile DatabaseHelper INSTANCE;

    public static DatabaseHelper getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseHelper.class, "Fin")
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract DBDAO dbdao();
    public abstract DateDAO datedao();

}


