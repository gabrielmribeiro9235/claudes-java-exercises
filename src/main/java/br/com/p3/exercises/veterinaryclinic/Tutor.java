package br.com.p3.exercises.veterinaryclinic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tutor {
    private static int ID_ATUAL = 1;

    private final int id;
    private final String nome;
    private String telefone;
    private final List<Pet> pets;

    public Tutor(String nome, String telefone) {
        this(ID_ATUAL++, nome, telefone);
    }

    public Tutor(int id, String nome, String telefone) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        pets = new ArrayList<>();
    }

    public void adicionarPet(Pet pet) {
        Objects.requireNonNull(pet, "Pet não pode ser nulo.");
        pets.add(pet);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return id == tutor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Pet> getPets() {
        return List.copyOf(pets);
    }
}
