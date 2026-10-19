package br.com.p2.practicetests.arena.modelo;

public class Arqueiro extends Personagem {
    private int ataques;

    public Arqueiro(String nome) {
        super(nome, 90);
    }

    @Override
    public int atacar() {
        ataques++;

        if (ataques % 3 == 0) return 28;

        return 14;
    }
}
