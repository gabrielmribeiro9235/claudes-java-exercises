package br.com.p2.exercises.fleetofdrones;

public interface Entregador {
    String id();
    boolean podeEntregar(Pedido pedido);
    double custo(Pedido pedido);
}
