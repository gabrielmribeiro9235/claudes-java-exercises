package br.com.p2.exercises.eventbus;

public class EventoErro extends Evento {
    private final int codigo;
    private final String mensagem;

    public EventoErro(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String descricao() {
        return "erro " + codigo + " - " + mensagem;
    }
}
