package br.com.p2.practicetests.melodify.modelo;

import br.com.p2.practicetests.melodify.servico.Filtros;

import java.util.*;

public class Playlist {
    private final String nome;
    private final Set<Midia> midias;

    public Playlist(String nome) {
        this.nome = nome;
        midias = new LinkedHashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean adicionar(Midia m) {
        Objects.requireNonNull(m, "A mídia a ser adicionada não pode ser nula.");

        return midias.add(m);
    }

    public int adicionarVarias(Midia ...m) {
        return Math.toIntExact(Arrays.stream(m).filter(this::adicionar).count());
    }

    public boolean remover(Midia m) {
        Objects.requireNonNull(m, "A mídia a ser removida não pode ser nula.");
        return midias.remove(m);
    }

    public int tamanho() {
        return midias.size();
    }

    public Iterator<Midia> iterador() {
        return midias.iterator();
    }

    public List<Midia> filtrar(Filtro<Midia> filtro) {
        return Filtros.filtrar(midias, filtro);
    }

    public int duracaoTotal() {
        return midias.stream().mapToInt(Midia::duracaoSegundos).sum();
    }

    public String duracaoTotalFormatada() {
        Reproduzivel aux = new Musica("", "", duracaoTotal(), null);

        return aux.duracaoFormatada();
    }
}
