package br.com.p2.practicetests.academicsecretary.servico;

import br.com.p2.practicetests.academicsecretary.dados.Utilitario;
import br.com.p2.practicetests.academicsecretary.modelo.Aluno;
import br.com.p2.practicetests.academicsecretary.modelo.Turma;

import java.util.ArrayList;
import java.util.List;

public class Importador {
    private final List<String> rejeitadas;

    public Importador() {
        rejeitadas = new ArrayList<>();
    }

    public int importar(Turma turma, List<String> linhas) {
        List<String> linhasCarregadas = Utilitario.carregarNotas();

        for (int i = 0; i < linhasCarregadas.size(); i++) {
            String linha = linhasCarregadas.get(i);

            String[] valores = linha.split(";");

            if (valores.length != 4) {
                rejeitadas.add("Linha " + (i + 1) + ": esperados 4 campos, encontrados " + valores.length);
                continue;
            }

            double nota;
            try {
                nota = Double.parseDouble(valores[3]);
            } catch (NumberFormatException e) {
                rejeitadas.add("Linha " + (i + 1) + ": nota nao numerica");
                continue;
            }

            try {
                Aluno aluno = turma.buscar(valores[0]);

                if (aluno == null) {
                    aluno = new Aluno(valores[1], valores[0]);

                    aluno.lancarNota(valores[2], nota);
                    turma.matricular(aluno);
                } else {
                    aluno.lancarNota(valores[2], nota);
                }
            } catch (RuntimeException e) {
                rejeitadas.add("Linha " + (i + 1) + ": " + e.getMessage());
            }
        }

        return linhas.size() - rejeitadas.size();
    }

    public List<String> getRejeitadas() {
        return List.copyOf(rejeitadas);
    }
}
