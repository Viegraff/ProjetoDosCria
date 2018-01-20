package com.example.projetodoscria.util;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ArquivoUtil {

    public void moverArquivo(String caminhoOriginal, String nomeArquivo, String caminhoNovo) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        Log.e("TESTDIR", caminhoOriginal + "/" + nomeArquivo);
        Log.e("TESTDIR", caminhoNovo + "/" + nomeArquivo);

        try {
            inputStream = new FileInputStream(caminhoOriginal + "/" + nomeArquivo);
            outputStream = new FileOutputStream(caminhoNovo + "/" + nomeArquivo);

            byte[] buffer = new byte[1024];
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            outputStream.flush();

            inputStream.close();
            outputStream.close();

            new File(caminhoOriginal + "/" + nomeArquivo).delete();

        } catch (Exception exception) {
            Log.e("XAMPSON", exception.getMessage());
        }
    }

}