package br.com.p2.practicetests.virtualstore.modelo;

public class ProdutoFisico extends Produto {
    private final double pesoKg;

    public ProdutoFisico(String sku, String nome, double preco, double pesoKg) {
        super(sku, nome, preco);
        this.pesoKg = pesoKg;
    }

    @Override
    public double frete(int quantidade) {
        return pesoKg * quantidade * 3;
    }

    @Override
    public boolean controlaEstoque() {
        return true;
    }
}
