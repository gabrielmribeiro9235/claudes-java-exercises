package br.com.p2.practicetests.melodify.servico;


import br.com.p2.practicetests.melodify.modelo.Filtro;
import br.com.p2.practicetests.melodify.modelo.Reproduzivel;

import java.util.Collection;
import java.util.List;

public class Filtros {
    private Filtros() {}

    public static <T extends Reproduzivel> List<T> filtrar(Collection<T> itens, Filtro<T> filtro) {
        return itens.stream().filter(filtro::aceita).toList();
    }
}
