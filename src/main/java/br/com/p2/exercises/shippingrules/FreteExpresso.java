package br.com.p2.exercises.shippingrules;

public class FreteExpresso implements RegraFrete {
    private final RegraFrete frete;

    public FreteExpresso(RegraFrete frete) {
        this.frete = frete;
    }

    @Override
    public double calcular(Pacote pacote) {
        return frete.calcular(pacote) * 1.8;
    }
}
