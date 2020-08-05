package com.dozarplati.zaim.ui.cards;

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
import com.dozarplati.zaim.adapters.AdapterCardCredit;
import com.dozarplati.zaim.adapters.AdapterCardDebet;
import com.dozarplati.zaim.adapters.AdapterCardInstallment;
import com.dozarplati.zaim.models.DB;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private cardsViewModel pageViewModel;
    private RecyclerView rec;
    AdapterCardCredit adapterCardCredit;
    AdapterCardDebet adapterCardDebet;
    AdapterCardInstallment adapterCardInstallment;
    int id = 0;

    PlaceholderFragment(int id){
        this.id = id;
    }

    PlaceholderFragment(){

    }

    public static PlaceholderFragment newInstance(int id) {
//        PlaceholderFragment fragment = new PlaceholderFragment(id);
//
//        return fragment;

        Bundle args = new Bundle();
        args.putInt("id", id);
        PlaceholderFragment f = new PlaceholderFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(cardsViewModel.class);
        id = getArguments().getInt("id");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cards_view, container, false);

        initGUI(root);

        return root;
    }

   void initGUI(View root){
       rec = (RecyclerView)root.findViewById(R.id.rec);
       listnerData();
   }


    void listnerData(){

        pageViewModel.getDB().observe(getViewLifecycleOwner(), new Observer<DB>() {
            @Override
            public void onChanged(DB db) {
                setupRecyclerView(db);
            }
        });

    }

    private void setupRecyclerView(DB list){
        switch (id){
            case 0:
                adapterCardCredit = new AdapterCardCredit(list.getCards_credit());
                rec.setLayoutManager(new LinearLayoutManager(getActivity()));

                if(Loader.getDb().getApp_config().getCards_credit_item().equals("1")) {
                    rec.setAdapter(adapterCardCredit);
                }
                else{
                    rec.setVisibility(View.GONE);
                    getView().findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
                }
                break;
            case 1:
                adapterCardDebet = new AdapterCardDebet(list.getCards_debit());
                rec.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(Loader.getDb().getApp_config().getCards_debit_item().equals("1")) {
                rec.setAdapter(adapterCardDebet);
                }
                else{
            rec.setVisibility(View.GONE);
            getView().findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            }
                break;
            case 2:
                adapterCardInstallment = new AdapterCardInstallment(list.getCards_installment());
                rec.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(Loader.getDb().getApp_config().getCards_installment_item().equals("1")) {
                rec.setAdapter(adapterCardInstallment);
                }
                else{
                    rec.setVisibility(View.GONE);
                    getView().findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
                }
                break;
        }


    }


}