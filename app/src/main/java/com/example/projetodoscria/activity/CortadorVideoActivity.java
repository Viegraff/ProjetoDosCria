package com.example.projetodoscria.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.projetodoscria.R;

import java.io.File;

import life.knowledge4.videotrimmer.K4LVideoTrimmer;
import life.knowledge4.videotrimmer.interfaces.OnK4LVideoListener;
import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener;

public class CortadorVideoActivity extends AppCompatActivity implements OnTrimVideoListener, OnK4LVideoListener {

    K4LVideoTrimmer videoTrimmer;

    static String DIRETORIO_VIDEO = "/storage/emulated/0/SmartCorner/Video/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortador);

        Intent extraIntent = getIntent();
        String path = "";

        if (extraIntent != null) {
            path = extraIntent.getStringExtra(MenuActivity.CAMINHO_ARQUIVO);
        }

        Toast.makeText(this, path, Toast.LENGTH_LONG).show();

        videoTrimmer = (K4LVideoTrimmer) findViewById(R.id.videoTrimmer);

        File file = new File(Environment.getExternalStorageDirectory(), DIRETORIO_VIDEO);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                file.mkdirs();
                Log.e("XAMPSON", "Falha ao criar diretório");
            }
        }

        if (videoTrimmer != null) {
            videoTrimmer.setMaxDuration(10);
            videoTrimmer.setOnTrimVideoListener(this);
            videoTrimmer.setOnK4LVideoListener(this);
            //videoTrimmer.setDestinationPath("/storage/emulated/0/SmartCorner/Video/");
            videoTrimmer.setVideoURI(Uri.parse(path));
            videoTrimmer.setVideoInformationVisibility(true);
        }

    }

    @Override
    public void onVideoPrepared() {
        Toast.makeText(this, "onVideoPrepared", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTrimStarted() {

    }

    @Override
    public void getResult(Uri uri) {
        startActivity(new Intent(Intent.ACTION_VIEW, uri).setDataAndType(uri, "video/mp4"));

        finish();
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
}