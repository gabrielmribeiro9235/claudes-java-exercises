package br.com.p3.exercises.library;

import java.util.Objects;

public class Livro {
    private static int ID_ATUAL = 1;

    private final int id;
    private final String titulo;
    private int exemplaresDisponiveis;

    public Livro(String titulo, int exemplaresDisponiveis) {
        this(ID_ATUAL++, titulo, exemplaresDisponiveis);
    }

    public Livro(int id, String titulo, int exemplaresDisponiveis) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Titulo não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.titulo = titulo;
        setExemplaresDisponiveis(exemplaresDisponiveis);
    }

    public void setExemplaresDisponiveis(int exemplaresDisponiveis) {
        if (exemplaresDisponiveis < 0) {
            throw new IllegalArgumentException("Exemplares disponíveis não pode ser negativo.");
        }
        this.exemplaresDisponiveis = exemplaresDisponiveis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", exemplaresDisponiveis=" + exemplaresDisponiveis +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }
}
