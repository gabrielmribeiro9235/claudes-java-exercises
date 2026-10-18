package br.com.p2.exercises.showtickets;

import java.util.Locale;

public class Ingresso {
    private final String evento;
    private final double valorBase;

    public Ingresso(String evento, double valorBase) {
        this.evento = evento;
        this.valorBase = valorBase;
    }

    public String getEvento() {
        return evento;
    }

    public double valorFinal() {
        return valorBase;
    }

    @Override
    public String toString() {
        return "Ingresso[" +
                "evento=" + evento +
                ", valorBase=" + String.format(Locale.US, "%.2f", valorBase) +
                ']';
    }
}
