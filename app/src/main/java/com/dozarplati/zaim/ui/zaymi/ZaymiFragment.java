package com.dozarplati.zaim.ui.zaymi;

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
import com.dozarplati.zaim.adapters.AdapterZaym;
import com.dozarplati.zaim.models.DB;


public class ZaymiFragment extends Fragment {

    private ZaymiModel zaymiModel;
    private RecyclerView rec;
    AdapterZaym adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zaymiModel =
                ViewModelProviders.of(this).get(ZaymiModel.class);
        View root = inflater.inflate(R.layout.fragment_zaymi, container, false);
        initGUI(root);

        return root;
    }

    void initGUI(View root){
        rec = (RecyclerView)root.findViewById(R.id.rec);
        try {
            if (Loader.getDb().getApp_config().getLoans_item().equals("1")) {
                listnerData(root);
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


    void listnerData(View root){

        zaymiModel.getDB().observe(getViewLifecycleOwner(), new Observer<DB>() {
            @Override
            public void onChanged(DB db) {
                if(db != null)
                setupRecyclerView(db);
                else{
                    rec.setVisibility(View.GONE);
                    root.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
                }
            }
        });

    }


    private void setupRecyclerView(DB list){
        adapter = new AdapterZaym(list.getLoans());
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setAdapter(adapter);

    }
}