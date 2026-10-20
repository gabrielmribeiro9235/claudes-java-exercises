package br.com.p2.practicetests.virtualstore.pagamento;

public class CartaoCredito implements Pagamento {
    private final int parcelas;

    public CartaoCredito(int parcelas) {
        if (parcelas < 1 || parcelas > 12) {
            throw new IllegalArgumentException("Parcelas devem estar entre 1 e 12: " + parcelas);
        }

        this.parcelas = parcelas;
    }

    @Override
    public String descricao() {
        return "CARTAO em " + parcelas + "x";
    }

    @Override
    public double total(double valor) {
        return  valor * (1 + 0.02 * (parcelas - 1));
    }
}
