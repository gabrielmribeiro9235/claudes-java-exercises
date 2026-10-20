package br.com.p2.practicetests.virtualstore.pagamento;

public class Pix implements Pagamento {
    @Override
    public String descricao() {
        return "PIX";
    }

    @Override
    public double total(double valor) {
        return 0.95 * valor;
    }
}
