package br.com.p3.exercises.veterinaryclinic;

public final class Outras extends Pet {
    public Outras(String nome, Tutor tutor) {
        super(nome, tutor);
    }

    public Outras(int id, String nome, Tutor tutor) {
        super(id, nome, tutor);
    }

    @Override
    public String especie() {
        return "OUTRAS";
    }
}
