package com.example.projetodoscria.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetodoscria.R;
import com.example.projetodoscria.adapter.Pager;

/**
 * Created by DrGreend on 17/01/2018.
 */

public class EtapaFragment extends Fragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_etapas, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        Drawable midia = getContext().getResources().getDrawable(R.drawable.midia);
        Drawable calendario = getContext().getResources().getDrawable(R.drawable.calendario);
        Drawable horario = getContext().getResources().getDrawable(R.drawable.horario);
        Drawable local = getContext().getResources().getDrawable(R.drawable.local);
        Drawable confirma = getContext().getResources().getDrawable(R.drawable.confirmar);


        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setIcon(midia));
        tabLayout.addTab(tabLayout.newTab().setIcon(calendario));
        tabLayout.addTab(tabLayout.newTab().setIcon(horario));
        tabLayout.addTab(tabLayout.newTab().setIcon(local));
        tabLayout.addTab(tabLayout.newTab().setIcon(confirma));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}