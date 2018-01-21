package com.example.projetodoscria.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetodoscria.R;
import com.example.projetodoscria.modelo.Monitores;
import com.example.projetodoscria.view.activity.MenuActivity;

import java.util.List;

/**
 * Created by DrGreend on 21/01/2018.
 */

public class AdapterMonitores extends ArrayAdapter<Monitores> implements View.OnClickListener{
    LayoutInflater layoutInflater;
    Integer resourceId;
    ImageButton btnAcao;

    public AdapterMonitores(Context context, int textViewResourceId, List<Monitores> monitoresList){//Mandar a classe mãe enviar o arraylist do mapaFragment para cá
        super(context, textViewResourceId, monitoresList);
        resourceId = textViewResourceId;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Monitores monitores = (Monitores) getItem(position);

        convertView = layoutInflater.inflate(resourceId, parent, false);

        TextView txtNome = (TextView) convertView.findViewById(R.id.textViewNomeMonitor);

        //TextView txtRua = (TextView) convertView.findViewById(R.id.textViewRuaMonitor);

        TextView txtPreco = (TextView) convertView.findViewById(R.id.textViewPrecoMonitor);

        btnAcao = (ImageButton) convertView.findViewById(R.id.buttonConfirmaMonitor);

        txtNome.setText(monitores.getNome());
        txtPreco.setText(String.valueOf(monitores.getPreco()));

        btnAcao.setOnClickListener(this);
        if(monitores.getStatus() == "disponivel") {
            btnAcao.setBackgroundResource(R.drawable.ok);
        } else {
            btnAcao.setBackgroundResource(R.drawable.cancelar);
            btnAcao.setEnabled(false);
        }
        //smartImageView.setImageUrl(imagem.getCaminho());
        //txtNome.setText(imagem.getNome().toString());
        return convertView;
    }

    @Override
    public void onClick(View view) {
            if (view.getId() == btnAcao.getId()) {
                Toast.makeText(getContext(), "Verdadeiro!!", Toast.LENGTH_SHORT).show();
        }
    }
}