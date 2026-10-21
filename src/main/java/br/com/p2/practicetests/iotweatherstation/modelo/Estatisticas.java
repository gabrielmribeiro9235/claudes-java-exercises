package br.com.p2.practicetests.iotweatherstation.modelo;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Estatisticas {
    private Estatisticas() {}

    public static <T extends Comparable<T>> T maximo(Collection<T> itens) {
        Objects.requireNonNull(itens, "A colecao nao pode ser nula");

        if (itens.isEmpty()) {
            throw new NoSuchElementException("Colecao vazia");
        }

        return itens.stream().max(T::compareTo).orElse(null);
    }

    public static <T extends Comparable<T>> List<T> ordenar(Collection<T> itens) {
        return itens.stream()
                .sorted(T::compareTo)
                .toList();
    }
}
