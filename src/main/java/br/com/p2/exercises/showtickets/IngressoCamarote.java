package br.com.p2.exercises.showtickets;

public final class IngressoCamarote extends IngressoVip {
    public IngressoCamarote(String evento, double valorBase, double taxaServico) {
        super(evento, valorBase, taxaServico);
    }

    @Override
    public double valorFinal() {
        return 1.2 * super.valorFinal();
    }

    @Override
    public String toString() {
        return super.toString() +
                " [CAMAROTE]";
    }
}
