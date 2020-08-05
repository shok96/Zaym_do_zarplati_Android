package com.dozarplati.zaim.ui.cards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dozarplati.zaim.Net.Loader;
import com.dozarplati.zaim.models.DB;


public class cardsViewModel extends ViewModel {

    private MutableLiveData<DB> db;

    public cardsViewModel() {
        db = new MutableLiveData<>();
        db.setValue(Loader.getDb());
        //ApiCalls.getDB(db);
    }

    public LiveData<DB> getDB() {
        return db;
    }

}