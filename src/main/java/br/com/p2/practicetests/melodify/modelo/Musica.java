package br.com.p2.practicetests.melodify.modelo;

public class Musica extends Midia {
    private final Genero genero;

    public Musica(String titulo, String autor, int duracaoSegundos, Genero genero) {
        super(titulo, autor, duracaoSegundos);
        this.genero = genero;
    }

    public Genero getGenero() {
        return genero;
    }

    @Override
    public String descricao() {
        return "Musica de " + super.getAutor() + " (" + genero + ")";
    }
}
