package com.example.projetodoscria.tabs;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetodoscria.R;
import com.example.projetodoscria.adapter.Pager;
import com.example.projetodoscria.adapter.PagerHorario;

/**
 * Created by DrGreend on 17/01/2018.
 */

public class TabHorario extends Fragment implements View.OnClickListener, TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_horario, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarHorarios);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutHorarios);
        Drawable relogio = getContext().getResources().getDrawable(R.drawable.relogio);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Todos os Horários"));
        tabLayout.addTab(tabLayout.newTab().setText("00:00 às 11:00"));
        tabLayout.addTab(tabLayout.newTab().setText("12:00 às 17:00"));
        tabLayout.addTab(tabLayout.newTab().setText("18:00 às 23:00"));
        tabLayout.addTab(tabLayout.newTab().setIcon(relogio));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pagerHorarios);

        //Creating our pager adapter
        PagerHorario adapter = new PagerHorario(getFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);


        return view;
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

    @Override
    public void onClick(View view) {

    }
}