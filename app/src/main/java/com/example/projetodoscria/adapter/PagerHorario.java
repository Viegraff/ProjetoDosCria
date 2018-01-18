package com.example.projetodoscria.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.projetodoscria.tabs.Tab4;
import com.example.projetodoscria.tabs.Tab5;
import com.example.projetodoscria.tabs.TabData;
import com.example.projetodoscria.tabs.TabHorario;
import com.example.projetodoscria.tabs.TabHorarios1;
import com.example.projetodoscria.tabs.TabHorarios2;
import com.example.projetodoscria.tabs.TabHorarios3;
import com.example.projetodoscria.tabs.TabHorarios4;
import com.example.projetodoscria.tabs.TabHorarios5;

/**
 * Created by DrGreend on 18/01/2018.
 */

public class PagerHorario extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public PagerHorario(FragmentManager fm, int tabCount) {
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
                TabHorarios1 tabHorarios1 = new TabHorarios1();

                return tabHorarios1;
            case 1:
                TabHorarios2 tabHorarios2 = new TabHorarios2();
                return tabHorarios2;
            case 2:
                TabHorarios3 tabHorarios3 = new TabHorarios3();
                return tabHorarios3;
            case 3:
                TabHorarios4 tabHorarios4 = new TabHorarios4();
                return tabHorarios4;
            case 4:
                TabHorarios5 tabHorarios5 = new TabHorarios5();
                return tabHorarios5;
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
