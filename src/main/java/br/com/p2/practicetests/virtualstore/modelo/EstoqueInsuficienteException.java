package br.com.p2.practicetests.virtualstore.modelo;

public class EstoqueInsuficienteException extends Exception {
    public EstoqueInsuficienteException(String sku, int solicitado, int disponivel) {
        super("Estoque insuficiente de " + sku + ": pedido " + solicitado + ", disponivel " + disponivel);
    }
}
