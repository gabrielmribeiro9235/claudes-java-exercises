package br.com.p3.exercises.veterinaryclinic;

import java.util.Objects;

public abstract sealed class Pet permits Cachorro, Gato, Outras {
    private static int ID_ATUAL = 1;

    private final int id;
    private final String nome;
    private final Tutor tutor;

    public Pet(String nome, Tutor tutor) {
        this(ID_ATUAL++, nome, tutor);
    }

    public Pet(int id, String nome, Tutor tutor) {
        Objects.requireNonNull(tutor, "Tutor não pode ser nulo.");
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.nome = nome;
        this.tutor = tutor;
    }

    public abstract String especie();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Tutor getTutor() {
        return tutor;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tutor=" + tutor +
                ", especie='" + especie() + '\'' +
                '}';
    }
}
