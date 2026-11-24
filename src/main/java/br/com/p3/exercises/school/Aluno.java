package br.com.p3.exercises.school;

import java.util.Objects;

public class Aluno {
    private static int ID_ATUAL = 1;

    private final int id;
    private final String nome;

    public Aluno(String nome) {
        this(ID_ATUAL++, nome);
    }

    public Aluno(int id, String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return id == aluno.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
