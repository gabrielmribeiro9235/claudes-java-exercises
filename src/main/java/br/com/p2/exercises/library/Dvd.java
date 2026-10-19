package br.com.p2.exercises.library;

public class Dvd extends Item {
    public Dvd(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int prazoDias() {
        return 3;
    }

    @Override
    public double multaPorDia() {
        return 3;
    }

    @Override
    public double calcularMulta(int diasComItem) {
        return super.calcularMulta(diasComItem) + 5;
    }
}
