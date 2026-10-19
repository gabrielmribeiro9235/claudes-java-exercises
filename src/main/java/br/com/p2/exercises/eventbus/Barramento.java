package br.com.p2.exercises.eventbus;

import java.util.*;

public class Barramento {
    public static final String TODOS = "*";
    private final Map<String, List<Ouvinte>> ouvintes;

    public Barramento() {
        ouvintes = new LinkedHashMap<>();

        String[] tipos = {TODOS, "EventoLogin", "EventoCompra", "EventoErro"};

        for (String tipo : tipos) {
            List<Ouvinte> lista = new ArrayList<>();

            ouvintes.put(tipo, lista);
        }
    }

    public void registrar(String tipoEvento, Ouvinte ouvinte) {
        Objects.requireNonNull(tipoEvento, "O tipo do evento não pode ser nulo.");
        Objects.requireNonNull(ouvinte, "O ouvinte não pode ser nulo.");

        if (ouvintes.containsKey(tipoEvento)) {
            List<Ouvinte> listaDoEvento = ouvintes.get(tipoEvento);
            listaDoEvento.add(ouvinte);

            ouvintes.replace(tipoEvento, listaDoEvento);
            return;
        }

        List<Ouvinte> lista = new ArrayList<>();
        lista.add(ouvinte);

        ouvintes.put(tipoEvento, lista);
    }

    public void registrarParaTodos(Ouvinte ouvinte) {
        Objects.requireNonNull(ouvinte, "Ouvinte não pode ser nulo.");

        List<Ouvinte> lista = ouvintes.get(TODOS);
        lista.add(ouvinte);

        ouvintes.replace(TODOS, lista);
    }

    public int publicar(Evento evento) {
        List<Ouvinte> todos = ouvintes.get(TODOS);
        List<Ouvinte> especifico = ouvintes.get(evento.getClass().getSimpleName());

        if (todos == null || especifico == null) return 0;

        int contador = 0;

        for (Ouvinte o : todos) {
            try {
                o.aoOcorrer(evento);
                contador++;
            } catch (RuntimeException e) {
                System.out.println("[Falha em ouvinte " + e.getMessage() + "]");
            }
        }

        for (Ouvinte o : especifico) {
            try {
                o.aoOcorrer(evento);
                contador++;
            } catch (RuntimeException e) {
                System.out.println("[falha em ouvinte: " + e.getMessage() + "]");
            }
        }

        return contador;
    }
}
