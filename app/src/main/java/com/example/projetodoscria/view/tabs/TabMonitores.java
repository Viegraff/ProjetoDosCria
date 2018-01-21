package com.example.projetodoscria.view.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.projetodoscria.R;
import com.example.projetodoscria.modelo.Monitores;
import com.example.projetodoscria.view.adapter.AdapterMonitores;
import com.example.projetodoscria.view.fragment.MapaFragment;

import java.util.ArrayList;

/**
 * Created by DrGreend on 18/01/2018.
 */

public class TabMonitores extends ListFragment {
    AdapterMonitores adapterMonitores;
    ListView listView;

    ArrayList<Monitores> monits = new ArrayList<Monitores>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_monitores, container, false);

        //String[] values = new String[] { "Message1", "Message2", "Message3" };
        /*MapaFragment mapaFragment = new MapaFragment();
        ArrayAdapter<Monitores> adapter = new ArrayAdapter<Monitores>(getActivity(), R.layout.adapter_monitores, mapaFragment.compartilhaMonitor());
                /*android.R.layout.simple_list_item_1, values);*/
        //setListAdapter(adapter);


        for (int i = 0; i < 4; i++) {
            monits.add(new Monitores("Ponto 0", -22.824371412016053, -43.30047223716974));
            monits.add(new Monitores("Ponto 1", -22.9, -43.1));
            monits.add(new Monitores("Ponto 2", -22.822915903955465, -43.29979196190834));
        }


        MapaFragment mapaFragment = new MapaFragment();
        adapterMonitores = new AdapterMonitores(getContext(), R.layout.adapter_monitores, monits);

        listView = (ListView) view.findViewById(R.id.listViewMonitores);

        listView.setAdapter(adapterMonitores);

        return view;
    }
}
