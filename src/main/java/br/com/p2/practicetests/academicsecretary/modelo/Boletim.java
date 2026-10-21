package br.com.p2.practicetests.academicsecretary.modelo;

import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;

public final class Boletim {
    private Boletim() {
    }

    public static String emitir(Aluno aluno) {
        Objects.requireNonNull(aluno, "aluno não pode ser nulo");

        StringBuilder builder = new StringBuilder(String.format("=========== BOLETIM: %s (%s) ===========\n", aluno.nome(), aluno.getProntuario()));

        builder.append("Disciplina   Notas                   Media\n");

        Iterator<String> disciplinas = aluno.disciplinas();

        while (disciplinas.hasNext()) {
            String disciplina = disciplinas.next();

            StringJoiner notas = new StringJoiner(" ");

            for (Double nota : aluno.notasDe(disciplina)) {
                notas.add(String.format("%.1f", nota));
            }

            builder.append(String.format("%-13s%-25s%.2f\n", disciplina, notas, aluno.mediaDisciplina(disciplina)));
        }

        builder.append("-------------------------------------------\n");

        builder.append(
                String.format(Locale.US, "Media geral: %.2f | %s",
                        aluno.media(),
                        (aluno.media() >= 6.0 ? "APROVADO" : "REPROVADO")
                )
        );

        return builder.toString();
    }
}
