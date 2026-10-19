package br.com.p2.exercises.eventbus;

public abstract class Evento {
    public Evento() {}

    public abstract String descricao();

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + descricao();
    }
}
