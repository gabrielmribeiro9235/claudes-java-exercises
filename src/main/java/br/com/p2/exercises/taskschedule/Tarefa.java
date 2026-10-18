package br.com.p2.exercises.taskschedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Tarefa implements Comparable<Tarefa> {
    private final String titulo;
    private final int prioridade;
    private final LocalDate prazo;
    private boolean isConcluida;

    public Tarefa(String titulo, int prioridade, LocalDate prazo) {
        this.titulo = titulo;
        this.prioridade = prioridade;
        this.prazo = prazo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public boolean isConcluida() {
        return isConcluida;
    }

    public void concluir() {
        isConcluida = true;
    }

    @Override
    public int compareTo(Tarefa o) {
        if (!prazo.equals(o.prazo)) return prazo.compareTo(o.prazo);

        if (prioridade != o.prioridade) return Integer.compare(o.prioridade, prioridade);

        return titulo.compareTo(o.titulo);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(titulo, tarefa.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    @Override
    public String toString() {
        return String.format("%s (p%d, %s%s)",
                titulo,
                prioridade,
                prazo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                isConcluida ? ", ok" : ""
        );
    }
}
