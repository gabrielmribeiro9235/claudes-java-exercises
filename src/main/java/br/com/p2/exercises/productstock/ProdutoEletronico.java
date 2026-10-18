package br.com.p2.exercises.productstock;

public class ProdutoEletronico extends Produto {
    private final int garantiaMeses;

    public ProdutoEletronico(String codigo, String nome, double preco, int garantiaMeses) {
        super(codigo, nome, preco);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    @Override
    public double frete() {
        return getPreco() * 0.02;
    }
}
