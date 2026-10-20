package br.com.p2.practicetests.virtualstore.modelo;

import java.util.Objects;

public abstract class Produto {
    private final String sku;
    private final String nome;
    private final double preco;

    public Produto(String sku, String nome, double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }

        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
    }

    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public abstract double frete(int quantidade);

    public abstract boolean controlaEstoque();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(sku, produto.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sku);
    }

    @Override
    public String toString() {
        return nome + " (" + sku + ")";
    }
}
