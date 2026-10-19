package br.com.p2.exercises.eventbus;

public class EventoLogin extends Evento {
    private final String usuario;

    public EventoLogin(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String descricao() {
        return usuario + " entrou";
    }
}
