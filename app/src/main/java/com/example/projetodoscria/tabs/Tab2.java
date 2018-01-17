package com.example.projetodoscria.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetodoscria.R;

/**
 * Created by DrGreend on 17/01/2018.
 */

public class Tab2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab_midia in you classes
        return inflater.inflate(R.layout.tab2, container, false);
    }
}
