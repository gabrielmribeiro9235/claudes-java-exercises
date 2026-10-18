package br.com.p2.exercises.productstock;

public abstract class Produto {
    private String codigo;
    private String nome;
    private double preco;

    public Produto(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public abstract double frete();

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto outro = (Produto) o;
        return codigo == outro.codigo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + codigo + ", " + nome + ", " + preco + "]";
    }
}
