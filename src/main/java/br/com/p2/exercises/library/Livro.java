package br.com.p2.exercises.library;

public class Livro extends Item {
    public Livro(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int prazoDias() {
        return 14;
    }

    @Override
    public double multaPorDia() {
        return 1;
    }
}
