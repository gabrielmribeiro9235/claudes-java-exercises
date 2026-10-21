package br.com.p2.practicetests.iotweatherstation.modelo;

import java.util.LinkedList;
import java.util.List;

public class Janela <T extends Number> {
    private final int capacidade;
    private final List<T> valores;

    public Janela(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser positiva: " + capacidade);
        }

        this.capacidade = capacidade;
        valores = new LinkedList<>();
    }

    public void adicionar(T valor) {
        if (tamanho() == capacidade) {
            valores.removeFirst();
        }

        valores.add(valor);
    }

    public int tamanho() {
        return valores.size();
    }

    public double media() {
        return valores.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }
}
