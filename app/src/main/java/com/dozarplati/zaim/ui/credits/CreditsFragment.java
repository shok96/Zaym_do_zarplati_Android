package com.dozarplati.zaim.ui.credits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dozarplati.zaim.Net.Loader;
import com.dozarplati.zaim.R;
import com.dozarplati.zaim.adapters.AdapterCredit;
import com.dozarplati.zaim.models.DB;


public class CreditsFragment extends Fragment {
    CreditsViewModel creditsViewModel;
    private RecyclerView rec;
    AdapterCredit adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        creditsViewModel =
                ViewModelProviders.of(this).get(CreditsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_credit, container, false);
        initGUI(root);

        return root;
    }

    void initGUI(View root) {
        rec = (RecyclerView) root.findViewById(R.id.rec);
        try{
        if (Loader.getDb().getApp_config().getCredits_item().equals("1")) {
            listnerData();
        } else {
            rec.setVisibility(View.GONE);
            root.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
        }
    }
    catch (Exception e){
        rec.setVisibility(View.GONE);
        root.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
    }
    }


    void listnerData(){

        creditsViewModel.getDB().observe(getViewLifecycleOwner(), new Observer<DB>() {
            @Override
            public void onChanged(DB db) {
                setupRecyclerView(db);
            }
        });

    }


    private void setupRecyclerView(DB list){
        adapter = new AdapterCredit(list.getCredits());
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setAdapter(adapter);

    }
}