package br.com.p2.practicetests.melodify.dados;

import java.util.List;

public final class Dados {
    private Dados() {
    }

    public static List<String> carregarCatalogo() {
        return List.of(
                "MUSICA;Bohemian Rhapsody;Queen;ROCK;355",
                "MUSICA;Billie Jean;Michael Jackson;POP;294",
                "MUSICA;Evidencias;Chitaozinho e Xororo;SERTANEJO;284",
                "MUSICA;Take Five;Dave Brubeck;JAZZ;324",
                "MUSICA;Another One Bites the Dust;Queen;ROCK;215",
                "MUSICA;Harder Better Faster;Daft Punk;ELETRONICA;224",
                "MUSICA;Thriller;Michael Jackson;POP;357",
                "PODCAST;Flow #42;Igor 3K;42;3600",
                "PODCAST;Nerdcast #10;Jovem Nerd;10;4200",
                "MUSICA;Hotel California;Eagles;ROCK;391"
        );
    }
}
