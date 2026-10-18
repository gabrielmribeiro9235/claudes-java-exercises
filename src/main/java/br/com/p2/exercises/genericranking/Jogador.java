package br.com.p2.exercises.genericranking;

import java.util.Objects;

public class Jogador implements Pontuavel {
    private final String nome;
    private final int pontos;

    public Jogador(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public int pontos() {
        return pontos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(nome, jogador.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return nome + "(" + pontos + ")";
    }
}
