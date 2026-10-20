package br.com.p2.practicetests.melodify.servico;

import br.com.p2.practicetests.melodify.modelo.Midia;
import br.com.p2.practicetests.melodify.modelo.Playlist;

import java.util.Iterator;
import java.util.Objects;

public class ResumoPlaylist {
    private ResumoPlaylist() {}

    public static String emitir(Playlist p) {
        Objects.requireNonNull(p, "A playlist não pode ser nula.");

        StringBuilder builder = new StringBuilder(String.format("=========== PLAYLIST: %s ===========\n", p.getNome()));

        Iterator<Midia> midiaIterator = p.iterador();

        int i = 0;
        while (midiaIterator.hasNext()) {
            i++;
            Midia m = midiaIterator.next();

            builder.append(i).append(". ").append(m).append("\n");
        }

        builder.append("----------------------------------------\n");
        builder.append(String.format("Faixas: %d | Duracao total: %s", p.tamanho(), p.duracaoTotalFormatada()));

        return builder.toString();
    }
}
