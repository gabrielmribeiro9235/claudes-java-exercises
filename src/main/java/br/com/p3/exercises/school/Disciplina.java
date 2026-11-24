package br.com.p3.exercises.school;

import java.util.Objects;

public class Disciplina {
    private static int ID_ATUAL = 1;

    private final int id;
    private final String nome;
    private final int cargaHoraria;

    public Disciplina(String nome, int cargaHoraria) {
        this(ID_ATUAL++, nome, cargaHoraria);
    }

    public Disciplina(int id, String nome, int cargaHoraria) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser um número positivo");
        }
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
}
