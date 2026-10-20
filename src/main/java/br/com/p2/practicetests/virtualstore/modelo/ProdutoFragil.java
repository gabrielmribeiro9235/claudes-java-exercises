package br.com.p2.practicetests.virtualstore.modelo;

public class ProdutoFragil extends ProdutoFisico {
    public ProdutoFragil(String sku, String nome, double preco, double pesoKg) {
        super(sku, nome, preco, pesoKg);
    }

    @Override
    public double frete(int quantidade) {
        return super.frete(quantidade) + 15;
    }
}
