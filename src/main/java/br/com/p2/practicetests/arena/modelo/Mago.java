package br.com.p2.practicetests.arena.modelo;

public class Mago extends Personagem implements Curandeiro  {
    private int mana;

    public Mago(String nome) {
        super(nome, 70);

        mana = 40;
    }

    @Override
    public int atacar() {
        if (mana >= 10) {
            mana -= 10;
            return 25;
        }

        return 3;
    }

    @Override
    public int curar(Personagem alvo) throws CuraIndisponivelException {
        if (mana < 15) {
            throw new CuraIndisponivelException(getNome() + " sem mana " + "(" + mana + ")");
        }

        mana -= 15;
        return alvo.receberCura(30);
    }
}
