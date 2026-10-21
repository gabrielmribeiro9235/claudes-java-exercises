package br.com.p2.practicetests.academicsecretary.main;

import br.com.p2.practicetests.academicsecretary.dados.Utilitario;
import br.com.p2.practicetests.academicsecretary.modelo.Aluno;
import br.com.p2.practicetests.academicsecretary.modelo.Boletim;
import br.com.p2.practicetests.academicsecretary.modelo.Professor;
import br.com.p2.practicetests.academicsecretary.modelo.Turma;
import br.com.p2.practicetests.academicsecretary.servico.Importador;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Principal {
    static void main() {
        Turma turma = new Turma("3A");
        Importador importador = new Importador();

        System.out.println("Linhas aceitas: " + importador.importar(turma, Utilitario.carregarNotas()));

        System.out.println("Alunos matriculados: " + turma.totalAlunos());

        importador.getRejeitadas().forEach(l -> System.out.println("\trejeitada -> " + l));

        Professor lucas = new Professor("Lucas", "PF001", "POO");

        Aluno eva = turma.buscar("SC3005");
        try {
            lucas.lancarNota(eva, 9.0);
            lucas.lancarNota(eva, -1);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.printf(Locale.US, "Media de %s em POO: %.1f%n", eva.nome().split(" ")[0], eva.mediaDisciplina("POO"));
        System.out.printf(Locale.US, "Media da turma: %.2f\n", turma.media());

        System.out.println("--- Ranking");

        List<Aluno> ranking = turma.ranking();

        ranking.forEach(aluno ->
                System.out.printf(Locale.US, "%-12s %.2f\n", aluno.nome(), aluno.media())
        );

        Map<String, List<Aluno>> alunosPorSituacao = turma.porSituacao();
        System.out.println("--- Situacao");

        alunosPorSituacao.forEach((key, value) ->
                System.out.printf("%s: %s;\n", key, String.join("; ", value.stream().map(Aluno::nome).toList()))
        );

        System.out.println(Boletim.emitir(turma.buscar("SC3001")));

        try {
            turma.matricular(new Aluno("Outra Ana", "SC3001"));
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
