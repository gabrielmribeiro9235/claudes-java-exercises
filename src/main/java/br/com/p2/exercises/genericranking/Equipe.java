package br.com.p2.exercises.genericranking;

import java.util.HashSet;
import java.util.Set;

public class Equipe implements Pontuavel {
    private final String nome;
    private final Set<Jogador> jogadores;

    public Equipe(String nome) {
        this.nome = nome;
        jogadores = new HashSet<>();
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public int pontos() {
        return jogadores.stream().mapToInt(Jogador::pontos).sum();
    }

    boolean adicionar(Jogador jogador) {
        return jogadores.add(jogador);
    }

    @Override
    public String toString() {
        return nome + "[" + pontos() + "]";
    }
}
