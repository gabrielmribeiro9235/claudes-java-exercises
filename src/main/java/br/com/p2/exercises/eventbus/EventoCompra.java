package br.com.p2.exercises.eventbus;

public class EventoCompra extends Evento {
    private final String usuario;
    private final double valor;

    public EventoCompra(String usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String descricao() {
        return usuario + " comprou " + valor;
    }
}
