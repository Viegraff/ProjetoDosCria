package com.example.projetodoscria.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projetodoscria.R;
import com.example.projetodoscria.modelo.Monitores;

import java.util.List;

/**
 * Created by DrGreend on 21/01/2018.
 */

public class AdapterMonitores extends ArrayAdapter<Monitores>{
    LayoutInflater layoutInflater;

    Integer resourceId;

    public AdapterMonitores(Context context, int textViewResourceId, List<Monitores> monitoresList){//Mandar a classe mãe enviar o arraylist do mapaFragment para cá
        super(context, textViewResourceId, monitoresList);

        this.layoutInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Monitores monitores = (Monitores) getItem(position);

        convertView = layoutInflater.inflate(resourceId, parent, false);

        TextView txtNome = (TextView) convertView.findViewById(R.id.textViewNomeMonitor);

        //TextView txtRua = (TextView) convertView.findViewById(R.id.textViewRuaMonitor);

        TextView txtPreco = (TextView) convertView.findViewById(R.id.textViewPrecoMonitor);

        ImageButton btnAcao = (ImageButton) convertView.findViewById(R.id.buttonConfirmaMonitor);

        txtNome.setText(monitores.getNome());
        txtPreco.setText(String.valueOf(monitores.getPreco()));
        //colocar if
        btnAcao.setBackgroundResource(R.drawable.ok);
        //smartImageView.setImageUrl(imagem.getCaminho());
        //txtNome.setText(imagem.getNome().toString());
        return convertView;
    }
}
