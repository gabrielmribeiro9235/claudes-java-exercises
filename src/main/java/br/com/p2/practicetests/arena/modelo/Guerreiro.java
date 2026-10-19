package br.com.p2.practicetests.arena.modelo;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 120);
    }

    @Override
    public int atacar() {
        return vida < 0.3 * getVidaMaxima() ? 27 : 18;
    }
}
