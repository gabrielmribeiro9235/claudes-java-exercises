package br.com.p2.practicetests.academicsecretary.modelo;

import java.util.*;

public class Turma implements Avaliavel {
    public static final double MEDIA_APROVACAO = 6.0;
    private final String nome;
    private final Map<String, Aluno> alunos;

    public Turma(String nome) {
        this.nome = nome;
        alunos = new TreeMap<>();
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public double media() {
        return alunos.values()
                .stream()
                .mapToDouble(Aluno::media)
                .average()
                .orElse(0);
    }

    public void matricular(Aluno aluno) {
        Objects.requireNonNull(aluno, "aluno não pode ser nulo");

        if (alunos.containsKey(aluno.getProntuario())) {
            throw new IllegalArgumentException("Aluno ja matriculado: " + aluno.getProntuario());
        }

        alunos.put(aluno.getProntuario(), aluno);
    }

    public Aluno buscar(String prontuario) {
        Objects.requireNonNull(prontuario, "prontuario não pode ser nulo");

        return alunos.get(prontuario);
    }

    public int totalAlunos() {
        return alunos.size();
    }

    private int compararAlunos(Aluno a1, Aluno a2) {
        if (a1.media() == a2.media()) {
            return a1.nome().compareTo(a2.nome());
        }

        return Double.compare(a2.media(), a1.media());
    }

    public List<Aluno> ranking() {
        return alunos.values()
                .stream()
                .sorted(this::compararAlunos)
                .toList();
    }

    public Map<String, List<Aluno>> porSituacao() {
        LinkedHashMap<String, List<Aluno>> alunosPorSituacao = new LinkedHashMap<>();

        List<Aluno> aprovados = alunos.values()
                .stream()
                .filter(a -> a.media() >= MEDIA_APROVACAO)
                .sorted(this::compararAlunos)
                .toList();

        List<Aluno> reprovados = alunos.values()
                .stream()
                .filter(a -> a.media() < MEDIA_APROVACAO)
                .sorted(this::compararAlunos)
                .toList();

        alunosPorSituacao.put("APROVADO", aprovados);
        alunosPorSituacao.put("REPROVADO", reprovados);

        return alunosPorSituacao;
    }
}
