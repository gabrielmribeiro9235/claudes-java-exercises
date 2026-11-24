
package br.com.p3.exercises.school;

import java.util.List;
import java.util.Locale;

public class Main {
    static void main() {
        DatabaseInitializer.init();
        SchoolManager manager = new SchoolManager();

        Aluno ana = new Aluno("Ana");
        Aluno bruno = new Aluno("Bruno");
        Aluno carla = new Aluno("Carla");
        Aluno diego = new Aluno("Diego");

        Disciplina algoritmos = new Disciplina("Algoritmos", 80);
        Disciplina bancoDados = new Disciplina("Banco de Dados", 60);
        Disciplina redes = new Disciplina("Redes", 40);

        System.out.println("=== CADASTRO DE ALUNOS ===");

        manager.adicionarAluno(ana);
        manager.adicionarAluno(bruno);
        manager.adicionarAluno(carla);
        manager.adicionarAluno(diego);

        System.out.println("Alunos cadastrados com sucesso.");

        System.out.println("\n=== CADASTRO DE DISCIPLINAS ===");

        manager.adicionarDisciplina(algoritmos);
        manager.adicionarDisciplina(bancoDados);
        manager.adicionarDisciplina(redes);

        System.out.println("Disciplinas cadastradas com sucesso.");

        System.out.println("\n=== MATRÍCULAS ===");

        matricular(manager, ana, algoritmos, 8, 9);
        matricular(manager, ana, bancoDados, 7, 8);
        matricular(manager, ana, redes, 9, 10);

        matricular(manager, bruno, algoritmos, 3, 2);
        matricular(manager, bruno, bancoDados, 3, 3);
        matricular(manager, bruno, redes, 7, 7);

        matricular(manager, carla, algoritmos, 5, 5);
        matricular(manager, carla, bancoDados, 6, 6);
        matricular(manager, carla, redes, 4, 4);

        matricular(manager, diego, algoritmos, 2, 3);
        matricular(manager, diego, bancoDados, 3, 3);

        manager.adicionarMatricula(diego, redes);

        System.out.println("Matrículas realizadas com sucesso.");

        System.out.println("\n=== BOLETIM DE DIEGO ===");

        List<Matricula> boletim = manager.boletim(diego.getId());

        for (Matricula matricula : boletim) {
            String situacao = matricula.getSituacao();

            String nota1 = situacao.equals("em andamento")
                    ? "pendente"
                    : String.format(Locale.ROOT, "%.1f", matricula.getNota1());

            String nota2 = situacao.equals("em andamento")
                    ? "pendente"
                    : String.format(Locale.ROOT, "%.1f", matricula.getNota2());

            String media = situacao.equals("em andamento")
                    ? "pendente"
                    : String.format(Locale.ROOT, "%.1f", matricula.getMedia());

            System.out.printf(
                    "Disciplina: %-15s | Nota 1: %-10s | Nota 2: %-10s | Média: %-10s | Situação: %s%n",
                    matricula.getDisciplina().getNome(),
                    nota1,
                    nota2,
                    media,
                    situacao
            );
        }

        System.out.println("\n=== RANKING DE ALUNOS ===");

        List<Aluno> ranking = manager.rankingAlunos();

        for (int i = 0; i < ranking.size(); i++) {
            System.out.printf("%dº lugar: %s%n", i + 1, ranking.get(i).getNome());
        }

        System.out.println("\n=== ALUNOS REPROVADOS EM MAIS DE UMA DISCIPLINA ===");

        List<Aluno> reprovados = manager.alunosReprovadosMaisDeUmaVez();

        for (Aluno aluno : reprovados) {
            System.out.println(aluno);
        }
    }

    private static void matricular(
            SchoolManager manager,
            Aluno aluno,
            Disciplina disciplina,
            double nota1,
            double nota2
    ) {
        manager.adicionarMatricula(aluno, disciplina);
        manager.lancarNotas(new Matricula(aluno, disciplina, nota1, nota2));
    }
}
