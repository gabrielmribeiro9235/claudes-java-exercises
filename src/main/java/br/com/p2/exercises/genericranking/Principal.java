package br.com.p2.exercises.genericranking;

import java.util.*;

public class Principal {
    static void main() {
        Jogador ana = new Jogador("Ana", 80);
        Jogador bia = new Jogador("Bia", 95);
        Jogador caio = new Jogador("Caio", 80);
        Jogador davi = new Jogador("Davi", 60);

        List<Jogador> jogadores = new ArrayList<>(List.of(ana, bia, caio, davi));
        System.out.println("Top 3 = " + Ranking.melhores(jogadores, 3));
        System.out.println("Top 10 = " + Ranking.melhores(jogadores, 10));
        System.out.println("Lista original intacta = " + jogadores);

        Equipe azul = new Equipe("Azul");
        azul.adicionar(ana);
        azul.adicionar(davi);
        System.out.println("Repetido entra? " + azul.adicionar(new Jogador("Ana", 1)));
        Equipe verde = new Equipe("Verde");
        verde.adicionar(bia);
        verde.adicionar(caio);
        Equipe vermelha = new Equipe("Vermelha");
        vermelha.adicionar(new Jogador("Eva", 200));

        Set<Equipe> equipes = new HashSet<>(List.of(azul, verde, vermelha));
        System.out.println("Equipes = " + Ranking.melhores(equipes, 2));
        System.out.println("Lider = " + Ranking.lider(equipes));
        System.out.println("Soma = " + Ranking.somaPontos(ana, bia, azul));
        try {
            Ranking.lider(new ArrayList<Jogador>());
        } catch (NoSuchElementException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
