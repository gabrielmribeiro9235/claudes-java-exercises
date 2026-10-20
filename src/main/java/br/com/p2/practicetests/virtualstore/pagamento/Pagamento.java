package br.com.p2.practicetests.virtualstore.pagamento;

public interface Pagamento {
    String descricao();
    double total(double valor);
}
