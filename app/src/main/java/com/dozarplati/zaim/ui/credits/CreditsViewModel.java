package com.dozarplati.zaim.ui.credits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dozarplati.zaim.Net.Loader;
import com.dozarplati.zaim.models.DB;


public class CreditsViewModel extends ViewModel {

    private MutableLiveData<DB> db;

    public CreditsViewModel() {
        db = new MutableLiveData<>();
        db.setValue(Loader.getDb());
        //ApiCalls.getDB(db);
    }

    public LiveData<DB> getDB() {
        return db;
    }
}