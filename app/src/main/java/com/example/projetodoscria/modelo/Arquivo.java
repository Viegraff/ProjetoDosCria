package com.example.projetodoscria.modelo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Arquivo implements Serializable {

    private String nomeArquivo;
    private String caminhoArquivo;
    private String tamanhoArquivo;
    private String formatoArquivo;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(String tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }
}