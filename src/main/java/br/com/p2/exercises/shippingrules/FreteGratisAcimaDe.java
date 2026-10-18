package br.com.p2.exercises.shippingrules;

public class FreteGratisAcimaDe implements RegraFrete {
    private final double limite;
    private final RegraFrete frete;

    public FreteGratisAcimaDe(double limite, RegraFrete frete) {
        this.limite = limite;
        this.frete = frete;
    }

    @Override
    public double calcular(Pacote pacote) {
        if (pacote.getValorDeclarado() >= limite) return 0;

        return frete.calcular(pacote);
    }
}
