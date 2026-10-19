package br.com.p2.exercises.library;

public class Revista extends Item {
    public Revista(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int prazoDias() {
        return 7;
    }

    @Override
    public double multaPorDia() {
        return 0.5;
    }
}
