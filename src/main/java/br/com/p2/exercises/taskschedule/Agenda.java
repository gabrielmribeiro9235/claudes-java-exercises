package br.com.p2.exercises.taskschedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Agenda {
    private final List<Tarefa> tarefas;

    public Agenda() {
        tarefas = new ArrayList<>();
    }

    public boolean adicionar(Tarefa tarefa) {
        if (tarefas.contains(tarefa)) return false;

        tarefas.add(tarefa);

        return true;
    }

    public boolean remover(String titulo) {
        Iterator<Tarefa> tarefaIterator = iterador();

        Tarefa tarefa = null;
        while (tarefaIterator.hasNext()) {
            Tarefa atual = tarefaIterator.next();

            if (atual.getTitulo().equals(titulo)) {
                tarefa = atual;
                break;
            }
        }

        tarefas.remove(tarefa);

        return tarefa != null;
    }

    public boolean concluir(String titulo) {
        Tarefa tarefa = tarefas.stream().filter(t -> titulo.equals(t.getTitulo())).findFirst().orElse(null);

        if (tarefa == null) return false;

        tarefa.concluir();

        return true;
    }

    public Iterator<Tarefa> iterador() {
        return tarefas.iterator();
    }

    public List<Tarefa> ordenadas() {
        return tarefas.stream().sorted(Tarefa::compareTo).toList();
    }

    public List<Tarefa> porPrioridade() {
        return tarefas.stream().sorted((t1, t2) ->{
            if (t1.getPrioridade() == t2.getPrioridade()) {
                return t1.getPrazo().compareTo(t2.getPrazo());
            }
            return Integer.compare(t2.getPrioridade(), t1.getPrioridade());
        }).toList();
    }

    public List<Tarefa> atrasadas(LocalDate hoje) {
        return tarefas.stream().filter(t -> !t.isConcluida() && hoje.isAfter(t.getPrazo())).toList();
    }
}
