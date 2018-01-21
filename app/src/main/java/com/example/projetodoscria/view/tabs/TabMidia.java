package com.example.projetodoscria.view.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetodoscria.R;
import com.example.projetodoscria.modelo.Arquivo;
import com.example.projetodoscria.util.ArquivoUtil;
import com.example.projetodoscria.view.activity.MenuActivity;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.File;

public class TabMidia extends Fragment {

    TextView textViewNomeArquivo, textViewTamanhoArquivo;
    DiscreteSeekBar seekBarTempo;

    ArquivoUtil arquivoUtil = new ArquivoUtil();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_midia, container, false);

        textViewNomeArquivo = view.findViewById(R.id.textViewNomeArquivo);
        textViewTamanhoArquivo = view.findViewById(R.id.textViewTamanhoArquivo);

        seekBarTempo = view.findViewById(R.id.seekBarTempo);
        seekBarTempo.setMin(1);
        seekBarTempo.setMax(3);
        seekBarTempo.setProgress(1);

        seekBarTempo.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value * 10;
            }
        });

        seekBarTempo.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int progress, boolean fromUser) {
                int interval = 1;
                progress = ((int) Math.round(progress / interval)) * interval;
                seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                int progressStep = seekBar.getMax() / 3;

                int lastDotProgress = Math.round(seekBar.getProgress() / progressStep) * progressStep;
                int nextDotProgress = lastDotProgress + progressStep;
                int midBetweenDots = lastDotProgress + (progressStep / 2);

                if (seekBar.getProgress() > midBetweenDots)
                    seekBar.setProgress(nextDotProgress);
                else
                    seekBar.setProgress(lastDotProgress);
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        Intent intent = getActivity().getIntent();
        Arquivo arquivo = new Arquivo();

        if (intent != null) {
            arquivo = (Arquivo) intent.getSerializableExtra("ARQUIVO");
        }

        textViewNomeArquivo.setText(arquivo.getNomeArquivo());
        textViewTamanhoArquivo.setText(arquivo.getTamanhoArquivo());

        return view;
    }


}
