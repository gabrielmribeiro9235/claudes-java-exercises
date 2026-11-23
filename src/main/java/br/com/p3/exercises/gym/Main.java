package br.com.p3.exercises.gym;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Main {
    static void main() {
        DatabaseInitializer.init();
        SqlitePlanoRepository planoRepository = new SqlitePlanoRepository();
        SqliteAlunoRepository alunoRepository = new SqliteAlunoRepository();

        Plano[] planos = {
                new Plano("básico", 100),
                new Plano("médio", 200),
                new Plano("completo", 300)
        };
        for (Plano plano : planos) {
            planoRepository.adicionarPlano(plano);
        }

        List<Plano> planosSalvos = planoRepository.getPlanos();

        Aluno[] alunos = {
                new Aluno("Gabriel", LocalDate.of(2006, 10, 24)),
                new Aluno("Maria", LocalDate.of(2005, 8, 18)),
                new Aluno("Pedro", LocalDate.of(2000, 1, 2)),
                new Aluno("Antonio", LocalDate.of(2008, 5, 30)),
                new Aluno("Juan", LocalDate.of(2007, 3, 11))
        };

        alunos[0].cadastrarPlano(planosSalvos.getFirst());
        alunos[1].cadastrarPlano(planosSalvos.getFirst());
        alunos[2].cadastrarPlano(planosSalvos.get(1));
        alunos[3].cadastrarPlano(planosSalvos.get(1));
        alunos[4].cadastrarPlano(planosSalvos.getLast());

        for (Aluno aluno : alunos) {
            alunoRepository.adicionarAluno(aluno);
        }

        System.out.println("Alunos no plano básico");
        alunoRepository.getAlunosPorPlano(planos[0]).forEach(System.out::println);

        System.out.println("\nAlunos no plano médio");
        alunoRepository.getAlunosPorPlano(planos[1]).forEach(System.out::println);

        System.out.println("\nAlunos no plano completo");
        alunoRepository.getAlunosPorPlano(planos[2]).forEach(System.out::println);

        System.out.println("\nIdade de cada aluno");
        alunoRepository.getAlunos().forEach(a -> {
            int idade = Period.between(a.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println(a.getNome() + ": " + idade + " anos");
        });

        System.out.println("\nDesativando alunos do plano básico");
        alunoRepository.getAlunosPorPlano(planos[0]).forEach(a -> {
            a.desativarAluno();
            alunoRepository.atualizarAluno(a);
            System.out.println(a);
        });

        System.out.printf("\nFaturamento mensal: R$ %.2f\n", planoRepository.faturamento());
    }
}
