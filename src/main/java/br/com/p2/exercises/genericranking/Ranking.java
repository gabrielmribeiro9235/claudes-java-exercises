package br.com.p2.exercises.genericranking;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class Ranking {
    private Ranking() {}

    public static <T extends Pontuavel> List<T> melhores(Collection<T> itens, int n) {
        return itens.stream().sorted((i1, i2) -> {
            if (i1.pontos() == i2.pontos()) {
                return i1.nome().compareTo(i2.nome());
            }
            return Integer.compare(i2.pontos(), i1.pontos());
        }).toList().subList(0, (Math.min(itens.size(), n)));
    }

    public static <T extends Pontuavel> T lider(Collection<T> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new NoSuchElementException("Nao ha participantes");
        }

        return melhores(itens, 1).getFirst();
    }

    public static int somaPontos(Pontuavel... itens) {
        return Arrays.stream(itens).mapToInt(Pontuavel::pontos).sum();
    }
}
