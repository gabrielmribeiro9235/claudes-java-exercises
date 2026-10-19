package br.com.p2.exercises.eventbus;

@FunctionalInterface
public interface Ouvinte {
    void aoOcorrer(Evento evento);
}
