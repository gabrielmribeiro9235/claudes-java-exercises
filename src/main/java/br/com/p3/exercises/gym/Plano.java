package br.com.p3.exercises.gym;

import java.util.Objects;

public class Plano {
    private static int ID_ATUAL = 1;

    private final int id;
    private final String nome;
    private final double mensalidade;

    public Plano(int id, String nome, double mensalidade) {
        this.id = id;
        this.nome = nome;
        this.mensalidade = mensalidade;
    }

    public Plano(String nome, double mensalidade) {
        this.id = ID_ATUAL++;
        this.nome = nome;
        this.mensalidade = mensalidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plano plano = (Plano) o;
        return id == plano.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Plano{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", mensalidade=" + mensalidade +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getMensalidade() {
        return mensalidade;
    }
}
