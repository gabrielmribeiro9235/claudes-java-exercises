package br.com.p2.practicetests.melodify.main;

import br.com.p2.practicetests.melodify.modelo.*;
import br.com.p2.practicetests.melodify.servico.Filtros;
import br.com.p2.practicetests.melodify.servico.ResumoPlaylist;

public class Principal {
    static void main() {
        Catalogo catalogo = new Catalogo();

        System.out.println("Midias no catalogo: " + catalogo.total());

        System.out.println("Generos: " + catalogo.generosDisponiveis());

        System.out.println("Rock: " + catalogo.musicasDoGenero(Genero.ROCK));

        System.out.println("Jazz: " + catalogo.musicasDoGenero(Genero.JAZZ).size() + " | Sertanejo: " + catalogo.musicasDoGenero(Genero.SERTANEJO).size());

        Usuario ana = new Usuario("Ana");
        Playlist treino = ana.criarPlaylist("Treino");

        System.out.println("Adicionadas de uma vez: " + treino.adicionarVarias(
                        catalogo.buscarPorTitulo("Harder Better Faster"),
                        catalogo.buscarPorTitulo("Thriller"),
                        catalogo.buscarPorTitulo("Thriller"),
                        catalogo.buscarPorTitulo("Another One Bites the Dust")
                )
        );

        System.out.println("Repetida entra? " + treino.adicionar(catalogo.buscarPorTitulo("Thriller")));

        System.out.println(ResumoPlaylist.emitir(treino));

        try {
            ana.criarPlaylist("Treino");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        int segundosOuvidos = 0;
        try {
            segundosOuvidos += ana.reproduzirPlaylist(treino.getNome());
            segundosOuvidos += ana.reproduzirPlaylist(treino.getNome());
        } catch (PlaylistInexistenteException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Segundos ouvidos: " + segundosOuvidos);

        try {
            ana.reproduzirPlaylist("Estudo");
        } catch (PlaylistInexistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println(ana.reproduzir(catalogo.buscarPorTitulo("Flow #42")));
        System.out.println(ana.reproduzir(catalogo.buscarPorTitulo("Thriller")));

        System.out.println("Mais tocadas: " + ana.maisTocadas(3));

        System.out.println("Longas da playlist: " + treino.filtrar(elemento -> elemento.duracaoSegundos() > 250));

        System.out.println("Rock curto: " + treino.filtrar(elemento ->
                elemento instanceof Musica e && e.getGenero().equals(Genero.ROCK) && e.duracaoSegundos() < 300));

        System.out.println(catalogo.buscarPorTitulo("Nerdcast #10").descricao());
    }
}
