package br.com.p2.practicetests.virtualstore.modelo;

public class ProdutoDigital extends Produto {
    public ProdutoDigital(String sku, String nome, double preco) {
        super(sku, nome, preco);
    }

    @Override
    public double frete(int quantidade) {
        return 0;
    }

    @Override
    public boolean controlaEstoque() {
        return false;
    }
}
