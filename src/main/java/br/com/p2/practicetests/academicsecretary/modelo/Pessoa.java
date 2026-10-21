package br.com.p2.practicetests.academicsecretary.modelo;

import java.util.Objects;

public abstract sealed class Pessoa permits Aluno, Professor {
    private final String nome;
    private final String prontuario;

    protected Pessoa(String nome, String prontuario) {
        if (nome == null || prontuario == null || nome.isBlank() || prontuario.isBlank()) {
            throw new IllegalArgumentException("Nome e prontuario sao obrigatorios");
        }

        this.nome = nome;
        this.prontuario = prontuario;
    }

    protected String getNome() {
        return nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(prontuario, pessoa.prontuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prontuario);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + nome + " (" + prontuario + ")";
    }
}
