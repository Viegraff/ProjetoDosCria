package com.example.projetodoscria.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.projetodoscria.tabs.TabData;
import com.example.projetodoscria.tabs.TabHorario;
import com.example.projetodoscria.tabs.Tab4;
import com.example.projetodoscria.tabs.Tab5;
import com.example.projetodoscria.tabs.TabMidia;

/**
 * Created by DrGreend on 17/01/2018.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                TabMidia tabMidia = new TabMidia();

                return tabMidia;
            case 1:
                TabData tabData = new TabData();
                return tabData;
            case 2:
                TabHorario tabHorario = new TabHorario();
                return tabHorario;
            case 3:
                Tab4 tab4 = new Tab4();
                return tab4;
            case 4:
                Tab5 tab5 = new Tab5();
                return tab5;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}