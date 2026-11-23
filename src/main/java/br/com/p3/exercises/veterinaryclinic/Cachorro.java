package br.com.p3.exercises.veterinaryclinic;

public final class Cachorro extends Pet {
    public Cachorro(String nome, Tutor tutor) {
        super(nome, tutor);
    }

    public Cachorro(int id, String nome, Tutor tutor) {
        super(id, nome, tutor);
    }

    @Override
    public String especie() {
        return "CACHORRO";
    }
}
