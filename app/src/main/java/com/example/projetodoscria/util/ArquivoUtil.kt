package com.example.projetodoscria.util

import java.io.File

class ArquivoUtil {

    fun getTamanhoArquivo(file: File): String {
        val tamanho = file.length() / 1024

        return if (tamanho >= 1024) {
            ((tamanho / 1024).toString() + "Mb")
        } else {
            (tamanho.toString() + "Kb")
        }
    }

}