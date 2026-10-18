package br.com.p2.exercises.shippingrules;

public class Pedido {
    private final Pacote pacote;
    private final RegraFrete regraFrete;

    public Pedido(Pacote pacote, RegraFrete regraFrete) {
        this.pacote = pacote;
        this.regraFrete = regraFrete;
    }

    public double frete() {
        return regraFrete.calcular(pacote);
    }

    public double total() {
        return pacote.getValorDeclarado() + frete();
    }
}
