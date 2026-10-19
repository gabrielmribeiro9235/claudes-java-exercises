package br.com.p2.practicetests.arena.modelo;

import java.util.Objects;

public abstract class Personagem implements Comparable<Personagem> {
    private final String nome;
    private final int vidaMaxima;
    protected int vida;

    protected Personagem(String nome, int vidaMaxima) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("nome não pode ser nulo nem estar em branco.");
        }

        if (vidaMaxima <= 0) {
            throw new IllegalArgumentException("vidaMaxima deve ser positivo.");
        }

        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        vida = vidaMaxima;
    }

    public String getNome() {
        return nome;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVida() {
        return vida;
    }

    public abstract int atacar();

    public boolean estaVivo() {
        return vida > 0;
    }

    public void receberDano(int dano) {
        if (dano < 0) {
            throw new IllegalArgumentException("dano deve ser positivo.");
        }

        vida = Math.max(0, vida - dano);
    }

    public int receberCura(int pontos) {
        if (!estaVivo()) {
            return 0;
        }
        int vidaInicial = vida;

        vida = Math.min(vidaMaxima, vida + pontos);

        return vida - vidaInicial;
    }

    @Override
    public int compareTo(Personagem o) {
        if (this.vida == o.vida) {
            return this.nome.compareTo(o.nome);
        }

        return Integer.compare(o.vida, this.vida);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Personagem that)) return false;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d/%d)",
                getClass().getSimpleName(),
                nome,
                vida,
                vidaMaxima
        );
    }
}

















