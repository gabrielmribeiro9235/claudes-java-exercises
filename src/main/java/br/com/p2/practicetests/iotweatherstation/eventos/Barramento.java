package br.com.p2.practicetests.iotweatherstation.eventos;

import br.com.p2.practicetests.iotweatherstation.modelo.Leitura;

import java.util.*;

public class Barramento {
    public static final String TODOS = "*";
    private final Map<String, List<Ouvinte>> ouvintes;
    private int falhasDeOuvintes;

    public Barramento() {
        ouvintes = new HashMap<>();
    }

    public void registrar(String tipoSensor, Ouvinte ouvinte) {
        Objects.requireNonNull(tipoSensor, "Tipo de sensor não pode ser nulo.");
        Objects.requireNonNull(ouvinte, "Ouvinte não pode ser nulo.");

        List<Ouvinte> lista = ouvintes.containsKey(tipoSensor) ? ouvintes.get(tipoSensor) : new ArrayList<>();
        lista.add(ouvinte);
        ouvintes.put(tipoSensor, lista);
    }

    public void registrarParaTodos(Ouvinte ouvinte) {
        Objects.requireNonNull(ouvinte, "Ouvinte não pode ser nulo.");

        registrar(TODOS, ouvinte);
    }

    public int publicar(Leitura<Double> leitura) {
        Objects.requireNonNull(leitura, "Leitura não pode ser nula.");

        List<Ouvinte> todos = ouvintes.get(TODOS);
        int concluidos = 0;

        for (Ouvinte o : todos){
            try {
                o.aoReceber(leitura);
                concluidos++;
            } catch (RuntimeException e) {
                falhasDeOuvintes++;
            }
        }

        List<Ouvinte> especifico = ouvintes.get(leitura.tipo());

        for (Ouvinte o : especifico) {
            try {
                o.aoReceber(leitura);
                concluidos++;
            } catch (RuntimeException e) {
                falhasDeOuvintes++;
            }
        }

        return concluidos;
    }

    public int getFalhasDeOuvintes() {
        return falhasDeOuvintes;
    }
}
