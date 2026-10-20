package br.com.p2.practicetests.melodify.modelo;

import java.util.*;

public class Usuario {
    private final String nome;
    private final Map<String, Playlist> playlists;
    private final Map<Midia, Integer> reproducoes;

    public Usuario(String nome) {
        this.nome = nome;
        playlists = new LinkedHashMap<>();
        reproducoes = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public Playlist criarPlaylist(String nome) {
        if (playlists.containsKey(nome)) {
            throw new IllegalArgumentException("Playlist ja existe: " + nome);
        }

        Playlist playlist = new Playlist(nome);

        playlists.put(nome, playlist);
        return playlist;
    }

    public Playlist getPlaylist(String nome) throws PlaylistInexistenteException {
        if (!playlists.containsKey(nome)) {
            throw new PlaylistInexistenteException("Playlist inexistente: " + nome);
        }

        return playlists.get(nome);
    }

    public String reproduzir(Midia m) {
        if (reproducoes.containsKey(m)) {
            reproducoes.replace(m, reproducoes.get(m) + 1);
        } else {
            reproducoes.put(m, 1);
        }

        return "Tocando: " + m.titulo() + " [" + m.duracaoFormatada() + "]";
    }

    public int reproduzirPlaylist(String nome) throws PlaylistInexistenteException {
        try {
            Playlist playlist = getPlaylist(nome);

            Iterator<Midia> midiaIterator = playlist.iterador();

            while (midiaIterator.hasNext()) {
                Midia m = midiaIterator.next();

                reproduzir(m);
            }

            return playlist.duracaoTotal();
        } catch (PlaylistInexistenteException e) {
            throw new PlaylistInexistenteException(e.getMessage());
        }
    }

    public int vezesTocada(Midia m) {
        return reproducoes.getOrDefault(m, 0);
    }

    public List<Midia> maisTocadas(int n) {
        return reproducoes.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    if (Objects.equals(e1.getValue(), e2.getValue())) {
                        return e1.getKey().titulo().compareTo(e2.getKey().titulo());
                    }
                    return e2.getValue().compareTo(e1.getValue());
                })
                .map(Map.Entry::getKey)
                .toList()
                .subList(0, n);
    }
}
