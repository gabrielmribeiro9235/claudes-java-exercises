package br.com.p2.practicetests.virtualstore.modelo;

@FunctionalInterface
public interface Notificador {
    void notificar(Pedido pedido);
}
