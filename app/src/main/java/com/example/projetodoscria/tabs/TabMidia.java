package com.example.projetodoscria.tabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetodoscria.R;
import com.example.projetodoscria.activity.MenuActivity;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class TabMidia extends Fragment {

    TextView textViewNomeArquivo, textViewTamanhoArquivo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_midia, container, false);

        textViewNomeArquivo = view.findViewById(R.id.textViewNomeArquivo);
        textViewTamanhoArquivo = view.findViewById(R.id.textViewTamanhoArquivo);

        Intent extraIntent = getActivity().getIntent();
        String caminhoArquivo = "";

        if (extraIntent != null) {
            caminhoArquivo = extraIntent.getStringExtra(MenuActivity.CAMINHO_ARQUIVO);
        }

        File file = new File(caminhoArquivo);

        textViewNomeArquivo.setText(file.getName().toString());
        textViewTamanhoArquivo.setText(retornaTamanhoArquivo(file));

        return view;
    }

    public String retornaTamanhoArquivo(File file) {
        long tamanho = file.length() / 1024;

        if (tamanho >= 1024) {
            return (tamanho / 1024 + "Mb").toString();
        } else {
            return (tamanho + "Kb").toString();
        }
    }

}
