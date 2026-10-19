package br.com.p2.exercises.eventbus;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class ContadorEventos implements Ouvinte {
    private final Map<String, Integer> contagem;

    public ContadorEventos() {
        contagem = new TreeMap<>();
    }

    @Override
    public void aoOcorrer(Evento evento) {
        Objects.requireNonNull(evento, "O evento não pode ser nulo.");

        if (contagem.containsKey(evento.getClass().getSimpleName())) {
            contagem.put(evento.getClass().getSimpleName(), contagem.get(evento.getClass().getSimpleName()) + 1);
            return;
        }

        contagem.put(evento.getClass().getSimpleName(), 1);
    }

    public Map<String, Integer> getContagem() {
        return new TreeMap<>(contagem);
    }
}
