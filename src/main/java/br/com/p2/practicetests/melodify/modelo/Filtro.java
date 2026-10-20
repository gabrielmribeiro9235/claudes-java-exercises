package br.com.p2.practicetests.melodify.modelo;

@FunctionalInterface
public interface Filtro<T> {
    boolean aceita(T elemento);
}
