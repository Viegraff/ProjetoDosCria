package com.example.projetodoscria.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetodoscria.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import life.knowledge4.videotrimmer.K4LVideoTrimmer;
import life.knowledge4.videotrimmer.interfaces.OnK4LVideoListener;
import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener;

import static com.example.projetodoscria.view.activity.MenuActivity.CAMINHO_ARQUIVO;

public class CortadorVideoActivity extends Activity implements OnTrimVideoListener, OnK4LVideoListener, View.OnClickListener {

    Button buttonManterOriginal;
    K4LVideoTrimmer videoTrimmer;

    InputStream inputStream;
    OutputStream outputStream;

    String caminhoVideoOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortador);

        buttonManterOriginal = (Button) findViewById(R.id.buttonManterOriginalVideo);
        buttonManterOriginal.setOnClickListener(this);

        videoTrimmer = (K4LVideoTrimmer) findViewById(R.id.videoTrimmer);

        Intent intent = getIntent();

        if (intent != null) {
            caminhoVideoOriginal = intent.getStringExtra(CAMINHO_ARQUIVO);
        }

        if (videoTrimmer != null) {
            videoTrimmer.setMaxDuration(10);
            videoTrimmer.setOnTrimVideoListener(this);
            videoTrimmer.setOnK4LVideoListener(this);
            videoTrimmer.setVideoURI(Uri.parse(caminhoVideoOriginal));
            videoTrimmer.setVideoInformationVisibility(true);
        }

    }

    @Override
    public void onVideoPrepared() {}

    @Override
    public void onTrimStarted() {}

    @Override
    public void getResult(Uri uri) {
        try {
            File arquivo = new File(uri.getPath());
            File diretorioVideo = new File(Environment.getExternalStorageDirectory() + "/SmartBusiness");//"/SmartCorner/Video/");
            File novoArquivo = new File(Environment.getExternalStorageDirectory() + "/SmartBusiness/", arquivo.getName());
            if (!diretorioVideo.exists()) {
                diretorioVideo.mkdir();
                inputStream = new FileInputStream(arquivo);
                //Log.e("Arquivo: ", arquivo.getAbsolutePath());
                System.out.println("Caminho: " + arquivo.getAbsolutePath());
                outputStream = new FileOutputStream(novoArquivo);

                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                outputStream.write(data);
                inputStream.close();
                outputStream.close();

            } else {
                inputStream = new FileInputStream(arquivo);
                outputStream = new FileOutputStream(novoArquivo);

                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                outputStream.write(data);
                inputStream.close();
                outputStream.close();
            }
            arquivo.delete();


            startActivity(new Intent(this, SelecionaMidiaActivity.class).putExtra(CAMINHO_ARQUIVO, novoArquivo.getPath()));

            finish();
        } catch (Exception exception) {
            Log.e("XAMPSON:", exception.getMessage());
        }
    }

    @Override
    public void cancelAction() {
        videoTrimmer.destroy();

        finish();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonManterOriginal.getId()) {
            startActivity(new Intent(this, SelecionaMidiaActivity.class).putExtra(CAMINHO_ARQUIVO, caminhoVideoOriginal));
        }
    }
}