package br.com.p2.exercises.productstock;

import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    private LocalDate validade;

    public ProdutoPerecivel(String codigo, String nome, double preco, LocalDate validade) {
        super(codigo, nome, preco);
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    @Override
    public double frete() {
        return 12.0;
    }
}
