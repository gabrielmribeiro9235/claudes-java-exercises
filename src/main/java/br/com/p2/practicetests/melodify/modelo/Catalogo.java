package br.com.p2.practicetests.melodify.modelo;

import br.com.p2.practicetests.melodify.dados.Dados;

import java.util.*;

public class Catalogo {
    private final Map<String, Midia> midiasPorTitulo;
    private final Map<Genero, List<Musica>> musicasPorGenero;

    public Catalogo() {
        midiasPorTitulo = new LinkedHashMap<>();
        musicasPorGenero = new TreeMap<>();

        carregar();
    }

    private void carregar() {
        Dados.carregarCatalogo().stream()
                .map(l -> l.split(";"))
                .forEach(midia -> {
                    if ("MUSICA".equals(midia[0])) {
                        Musica musica = new Musica(midia[1], midia[2], Integer.parseInt(midia[4]), Genero.valueOf(midia[3]));
                        midiasPorTitulo.put(midia[1], musica);

                        if (musicasPorGenero.containsKey(musica.getGenero())) {
                            musicasPorGenero.get(musica.getGenero()).add(musica);
                        } else {
                            List<Musica> lista = new ArrayList<>();
                            lista.add(musica);

                            musicasPorGenero.put(musica.getGenero(), lista);
                        }
                    } else {
                        Podcast podcast = new Podcast(midia[1], midia[2], Integer.parseInt(midia[4]), Integer.parseInt(midia[3]));

                        midiasPorTitulo.put(podcast.titulo(), podcast);
                    }
                });
    }

    public int total() {
        return midiasPorTitulo.size();
    }

    public Midia buscarPorTitulo(String t) {
        return midiasPorTitulo.get(t);
    }

    public List<Musica> musicasDoGenero(Genero g) {
        return musicasPorGenero.containsKey(g) ? List.copyOf(musicasPorGenero.get(g)) : Collections.emptyList();
    }

    public Set<Genero> generosDisponiveis() {
        return musicasPorGenero.keySet();
    }
}
