package br.com.p2.practicetests.arena.modelo;

public class Clerigo extends Personagem implements Curandeiro {
    private int usosDeCura;

    public Clerigo(String nome) {
        super(nome, 100);

        usosDeCura = 2;
    }

    @Override
    public int atacar() {
        return 8;
    }

    @Override
    public int curar(Personagem alvo) throws CuraIndisponivelException {
        if (usosDeCura == 0) {
            throw new CuraIndisponivelException(getNome() + " sem usos de cura.");
        }

        usosDeCura--;
        return alvo.receberCura(35);
    }
}
