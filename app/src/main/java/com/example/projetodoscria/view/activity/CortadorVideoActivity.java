package com.example.projetodoscria.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.projetodoscria.R;
import com.example.projetodoscria.util.ArquivoUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import life.knowledge4.videotrimmer.K4LVideoTrimmer;
import life.knowledge4.videotrimmer.interfaces.OnK4LVideoListener;
import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener;

public class CortadorVideoActivity extends Activity implements OnTrimVideoListener, OnK4LVideoListener {

    ArquivoUtil arquivoUtil = new ArquivoUtil();
    InputStream inputStream = null;
    OutputStream outputStream = null;

    K4LVideoTrimmer videoTrimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortador);

        videoTrimmer = (K4LVideoTrimmer) findViewById(R.id.videoTrimmer);

        Intent intent = getIntent();
        String caminhoVideo = null;

        if (intent != null) {
            caminhoVideo = intent.getStringExtra(MenuActivity.CAMINHO_ARQUIVO);
        }

        if (videoTrimmer != null) {
            videoTrimmer.setMaxDuration(10);
            videoTrimmer.setOnTrimVideoListener(this);
            videoTrimmer.setOnK4LVideoListener(this);
            videoTrimmer.setVideoURI(Uri.parse(caminhoVideo));
            videoTrimmer.setVideoInformationVisibility(true);
        }

    }

    @Override
    public void onVideoPrepared() {
        Toast.makeText(this, "onVideoPrepared", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTrimStarted() {
        Toast.makeText(this, "onTrimStarted", Toast.LENGTH_LONG).show();
    }

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
                System.out.println("Caminho: "+arquivo.getAbsolutePath());
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
            finish();
        } catch (Exception exception) {
            Log.e("ERRO:", exception.getMessage());
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

   /* public static boolean createDirIfNotExists(String path) {
        boolean ret = true;

        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("XAMPSON", "Problem creating Image folder");
                ret = false;
            }
        }
        return ret;
    }*/
}