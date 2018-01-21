package com.example.projetodoscria.view.tabs;

import android.content.Context;
import android.content.Intent;
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

        Intent intent = getActivity().getIntent();

        if(intent != null){
            monits = (ArrayList<Monitores>) intent.getSerializableExtra("monits");
        }

           /* monits.add(new Monitores("Ponto 0", -22.824371412016053, -43.30047223716974, 6.93, "disponivel"));
            monits.add(new Monitores("Ponto 1", -22.9, -43.1, 7.35, "disponivel"));
            monits.add(new Monitores("Ponto 2", -22.822915903955465, -43.29979196190834, 6.93, "indisponivel"));*/



        AdapterMonitores adapter = new AdapterMonitores(getActivity(), R.layout.adapter_monitores, monits);
        setListAdapter(adapter);

        return view;
    }
}
