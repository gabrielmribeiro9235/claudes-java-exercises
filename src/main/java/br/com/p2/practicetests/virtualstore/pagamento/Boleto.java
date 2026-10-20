package br.com.p2.practicetests.virtualstore.pagamento;

public class Boleto implements Pagamento {
    @Override
    public String descricao() {
        return "BOLETO";
    }

    @Override
    public double total(double valor) {
        return valor + 3.5;
    }
}
