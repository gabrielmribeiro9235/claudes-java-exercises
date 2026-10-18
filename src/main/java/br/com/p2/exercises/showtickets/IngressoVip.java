package br.com.p2.exercises.showtickets;

import java.util.Locale;

public class IngressoVip extends Ingresso {
    private final double taxaServico;

    public IngressoVip(String evento, double valorBase, double taxaServico) {
        super(evento, valorBase);
        this.taxaServico = taxaServico;
    }

    @Override
    public double valorFinal() {
        return super.valorFinal() + taxaServico;
    }

    @Override
    public String toString() {
        return super.toString() +
                " [VIP, " +
                "taxa=" + taxaServico +
                "]";
    }
}
