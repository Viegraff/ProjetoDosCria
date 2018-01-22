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
import com.example.projetodoscria.util.AuxiliarMonitor;
import com.example.projetodoscria.view.adapter.AdapterMonitores;
import com.example.projetodoscria.view.fragment.MapaFragment;

import java.util.ArrayList;

public class TabMonitores extends ListFragment {
    AdapterMonitores adapterMonitores;
    ListView listView;
    ArrayList<Monitores> monits = new ArrayList<Monitores>();

    AuxiliarMonitor auxiliarMonitor = AuxiliarMonitor.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_monitores, container, false);

        monits = auxiliarMonitor.listarMonitores();

        AdapterMonitores adapter = new AdapterMonitores(getActivity(), R.layout.adapter_monitores, monits);
        setListAdapter(adapter);

        return view;
    }
}
