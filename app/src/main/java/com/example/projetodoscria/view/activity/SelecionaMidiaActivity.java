package com.example.projetodoscria.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.projetodoscria.R;
import com.example.projetodoscria.modelo.Arquivo;
import com.example.projetodoscria.util.ArquivoUtil;

import java.io.File;

import static com.example.projetodoscria.view.activity.MenuActivity.CAMINHO_ARQUIVO;

public class SelecionaMidiaActivity extends AppCompatActivity {

    Arquivo arquivo;
    ArquivoUtil arquivoUtil = new ArquivoUtil();

    File arquivoFile;

    String formatoArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String caminhoArquivo = null;

        if (intent != null) {
            caminhoArquivo = intent.getStringExtra(CAMINHO_ARQUIVO);
        }

        arquivoFile = new File(caminhoArquivo);

        formatoArquivo = arquivoFile.getAbsolutePath().substring(arquivoFile.getAbsolutePath().lastIndexOf("."));

        arquivo = new Arquivo();

        arquivo.setCaminhoArquivo(arquivoFile.getPath());
        arquivo.setFormatoArquivo(formatoArquivo);
        arquivo.setNomeArquivo(arquivoFile.getName());
        arquivo.setTamanhoArquivo(arquivoUtil.getTamanhoArquivo(arquivoFile));

        if (formatoArquivo.equals(".jpeg") || formatoArquivo.equals(".png")) {
            setContentView(R.layout.activity_seleciona_imagem);

            onCreateImagem();
        }

        if (formatoArquivo.equals(".mp4")) {
            setContentView(R.layout.activity_seleciona_video);

            onCreateVideo();
        }

    }

    public void onCreateImagem() {
        Button buttonAvancar, buttonCancelar, buttonMudarImagem;
        ImageView imageViewMidia;
        TextView textViewNomeImagem, textViewTamanhoImagem;

        buttonAvancar = (Button) findViewById(R.id.buttonAvancarImagem);
        buttonCancelar = (Button) findViewById(R.id.buttonCancelarImagem);
        buttonMudarImagem = (Button) findViewById(R.id.buttonMudarImagem);

        imageViewMidia = (ImageView) findViewById(R.id.imageViewMidia);

        textViewNomeImagem = (TextView) findViewById(R.id.textViewNomeImagem);
        textViewTamanhoImagem = (TextView) findViewById(R.id.textViewTamanhoImagem);

        buttonAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EnviarPropagandaActivity.class).putExtra("ARQUIVO", arquivo));
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
        buttonMudarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

        imageViewMidia.setBackground(null);
        imageViewMidia.setImageURI(Uri.fromFile(arquivoFile));

        textViewNomeImagem.setText(arquivoFile.getName());
        textViewTamanhoImagem.setText(arquivoUtil.getTamanhoArquivo(arquivoFile));
    }

    public void onCreateVideo() {
        Button buttonAvancar, buttonCancelar, buttonMudarVideo;
        VideoView videoViewMidia;
        TextView textViewNomeVideo, textViewTamanhoVideo;

        buttonAvancar = (Button) findViewById(R.id.buttonAvancarVideo);
        buttonCancelar = (Button) findViewById(R.id.buttonCancelarVideo);
        buttonMudarVideo = (Button) findViewById(R.id.buttonMudarVideo);

        videoViewMidia = (VideoView) findViewById(R.id.videoViewMidia);

        textViewNomeVideo = (TextView) findViewById(R.id.textViewNomeVideo);
        textViewTamanhoVideo = (TextView) findViewById(R.id.textViewTamanhoVideo);

        buttonAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EnviarPropagandaActivity.class).putExtra("ARQUIVO", arquivo));
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
        buttonMudarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

        videoViewMidia.setBackground(null);
        videoViewMidia.setVideoPath(arquivoFile.getPath());
        videoViewMidia.start();

        textViewNomeVideo.setText(arquivoFile.getName());
        textViewTamanhoVideo.setText(arquivoUtil.getTamanhoArquivo(arquivoFile));
    }
}