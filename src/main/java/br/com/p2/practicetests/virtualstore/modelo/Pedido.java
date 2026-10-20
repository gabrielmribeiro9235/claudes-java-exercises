package br.com.p2.practicetests.virtualstore.modelo;

import java.util.Locale;

public class Pedido {
    private final String id;
    private final String formaPagamento;
    private final double valorBruto;
    private double total;

    public Pedido(String id, String formaPagamento, double valorBruto, double total) {
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.valorBruto = valorBruto;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,"Pedido %s | %s | bruto=%.2f | total=%.2f",
                id,
                formaPagamento,
                valorBruto,
                total
        );
    }
}
