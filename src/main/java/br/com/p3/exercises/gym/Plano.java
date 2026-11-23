package br.com.p3.exercises.gym;

import java.util.Objects;

public class Plano {
    private final String nome;
    private final double mensalidade;

    public Plano(String nome, double mensalidade) {
        this.nome = nome;
        this.mensalidade = mensalidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plano plano = (Plano) o;
        return Objects.equals(nome, plano.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return "Plano{" +
                "nome='" + nome + '\'' +
                ", mensalidade=" + mensalidade +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public double getMensalidade() {
        return mensalidade;
    }
}
