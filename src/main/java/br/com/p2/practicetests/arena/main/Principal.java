package br.com.p2.practicetests.arena.main;

import br.com.p2.practicetests.arena.jogo.Arena;
import br.com.p2.practicetests.arena.modelo.*;

public class Principal {
    static final int MAX_RODADAS = 40;

    static void main() {
        Arena arena = new Arena("Dragoes", "Fenix");

        Personagem[] dragoes = {
                new Guerreiro("Thor"),
                new Mago("Merlin"),
                new Clerigo("Aria")
        };

        for (Personagem p : dragoes) {
            arena.adicionar("Dragoes", p);
        }

        Personagem[] fenix = {
                new Arqueiro("Legolas"),
                new Guerreiro("Conan"),
                new Arqueiro("Robin")
        };

        for (Personagem p : fenix) {
            arena.adicionar("Fenix", p);
        }

        try {
            arena.adicionar("Fenix", new Mago("Thor"));
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        while (arena.vencedor() == null && arena.getRodada() < MAX_RODADAS) {
            System.out.print(arena.executarRodada());
        }

        System.out.println("=== Vencedor: " + arena.vencedor() + " apos " + arena.getRodada() + " rodadas");

        System.out.println("--- Ranking");

        arena.ranking().forEach(System.out::println);

        System.out.println("--- Dano causado");

        arena.danoCausado().forEach((key, value) -> System.out.println(key + " = " + value));

    }
}
