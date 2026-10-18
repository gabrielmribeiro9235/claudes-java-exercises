package br.com.p2.exercises.taskschedule;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class Principal {
    static void main() {
        Agenda agenda = new Agenda();
        System.out.println(agenda.adicionar(new Tarefa("Estudar Collections", 5, LocalDate.of(2026, 10, 20))));
        System.out.println(agenda.adicionar(new Tarefa("Lavar o carro", 2, LocalDate.of(2026, 10, 18))));
        System.out.println(agenda.adicionar(new Tarefa("Entregar relatorio", 4, LocalDate.of(2026, 10, 18))));
        System.out.println(agenda.adicionar(new Tarefa("Pagar boleto", 3, LocalDate.of(2026, 10, 15))));
        System.out.println(agenda.adicionar(new Tarefa("Lavar o carro", 1, LocalDate.of(2026, 11, 1))));

        System.out.println("-- ordenadas (prazo, prioridade desc, titulo)");
        for (Tarefa t : agenda.ordenadas()) System.out.println(t);

        System.out.println("-- por prioridade");
        for (Tarefa t : agenda.porPrioridade()) System.out.println(t);

        agenda.concluir("Pagar boleto");
        List<Tarefa> atrasadas = agenda.atrasadas(LocalDate.of(2026, 10, 19));
        System.out.println("-- atrasadas em 2026-10-19: " + atrasadas);

        System.out.println("remover(Lavar o carro) = " + agenda.remover("Lavar o carro"));
        System.out.println("remover(Inexistente) = " + agenda.remover("Inexistente"));

        System.out.println("-- iterador");
        Iterator<Tarefa> it = agenda.iterador();
        while (it.hasNext()) System.out.println(it.next());
    }
}
