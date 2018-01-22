package com.example.projetodoscria.util;

import com.example.projetodoscria.modelo.Monitores;

import java.util.ArrayList;

public class AuxiliarMonitor {
    ArrayList<Monitores> arrayMonitores = new ArrayList<Monitores>();

    private static AuxiliarMonitor auxiliarMonitor;

    public static AuxiliarMonitor getInstance() {
        if (auxiliarMonitor == null) {
            auxiliarMonitor = new AuxiliarMonitor();
        }
        return auxiliarMonitor;
    }

    public void adicionarMonitor(Monitores monitores) {
        arrayMonitores.add(monitores);
    }

    public ArrayList<Monitores> listarMonitores() {
        return arrayMonitores;
    }
}
