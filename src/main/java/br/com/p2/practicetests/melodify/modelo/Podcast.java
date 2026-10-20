package br.com.p2.practicetests.melodify.modelo;

public class Podcast extends Midia {
    private final int episodio;

    public Podcast(String titulo, String autor, int duracaoSegundos, int episodio) {
        super(titulo, autor, duracaoSegundos);
        this.episodio = episodio;
    }

    @Override
    public String descricao() {
        return "Podcast de " + super.getAutor() + ", episodio " + episodio;
    }
}
