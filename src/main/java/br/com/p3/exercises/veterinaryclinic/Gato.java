package br.com.p3.exercises.veterinaryclinic;

public final class Gato extends Pet {
    public Gato(String nome, Tutor tutor) {
        super(nome, tutor);
    }

    public Gato(int id, String nome, Tutor tutor) {
        super(id, nome, tutor);
    }

    @Override
    public String especie() {
        return "GATO";
    }
}
