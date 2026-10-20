package br.com.p2.practicetests.melodify.modelo;

public interface Reproduzivel {
    String titulo();
    int duracaoSegundos();

    default String duracaoFormatada() {
        return (duracaoSegundos() / 60 >= 10 ? duracaoSegundos() / 60 : "0" + (duracaoSegundos() / 60)) +
                ":" +
                (duracaoSegundos() % 60 >= 10 ? duracaoSegundos() % 60 : "0" + (duracaoSegundos() % 60));
    }
}
