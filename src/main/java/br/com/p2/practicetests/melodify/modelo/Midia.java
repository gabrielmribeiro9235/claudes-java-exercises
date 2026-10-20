package br.com.p2.practicetests.melodify.modelo;

import java.util.Objects;

public abstract class Midia implements Reproduzivel {
    private final String titulo;
    private final String autor;
    private final int duracaoSegundos;

    public Midia(String titulo, String autor, int duracaoSegundos) {
        this.titulo = titulo;
        this.autor = autor;
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public int duracaoSegundos() {
        return duracaoSegundos;
    }

    public abstract String descricao();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Midia midia)) return false;
        return Objects.equals(titulo, midia.titulo) && Objects.equals(autor, midia.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor);
    }

    @Override
    public String toString() {
        return String.format("%s - %s [%s]",
                titulo,
                autor,
                duracaoFormatada()
        );
    }
}
