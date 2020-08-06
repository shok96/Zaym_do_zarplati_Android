package com.dozarplati.zaim.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dozarplati.zaim.Net.Loader;
import com.dozarplati.zaim.R;
import com.google.android.material.tabs.TabLayout;


public class cardsFragment extends Fragment {

    private TabLayout tabs;
    private cardsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cards, container, false);
        this.root = root;
        initGUI(root);
        load();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    void initGUI(View root){
        viewPager = root.findViewById(R.id.view_pager);
        tabs = root.findViewById(R.id.tabs);
        //viewPager.removeAllViews();


    }

    void load(){
        sectionsPagerAdapter = new cardsPagerAdapter(getActivity(), getParentFragmentManager());
        try {
            if (Loader.getDb().getApp_config().getCards_item().equals("1")) {
                viewPager.setAdapter(sectionsPagerAdapter);
            } else {
                viewPager.setVisibility(View.GONE);
                root.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            }
        }
        catch (Exception e){
            if(viewPager.getAdapter() == null){
                viewPager.setVisibility(View.GONE);
                root.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            }
        }
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        //initGUI(root);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}