package br.com.p2.practicetests.academicsecretary.modelo;

import java.util.*;

public final class Aluno extends Pessoa implements Avaliavel {
    private final Map<String, List<Double>> notas;

    public Aluno(String nome, String prontuario) {
        super(nome, prontuario);

        notas = new TreeMap<>();
    }

    @Override
    public String nome() {
        return super.getNome();
    }

    @Override
    public double media() {
        return notas.keySet()
                .stream()
                .mapToDouble(this::mediaDisciplina)
                .average()
                .orElse(0);
    }

    public void lancarNota(String disciplina, double nota) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula");

        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota fora do intervalo 0..10: " + nota);
        }

        List<Double> lista = notas.containsKey(disciplina) ? notas.get(disciplina) : new ArrayList<>();
        lista.add(nota);

        notas.put(disciplina, lista);
    }

    public double mediaDisciplina(String disciplina) {
        if (!notas.containsKey(disciplina) || notas.get(disciplina).isEmpty()) {
            throw new IllegalArgumentException("Sem notas em " + disciplina);
        }

        return notas.get(disciplina)
                .stream()
                .mapToDouble(n -> n)
                .average()
                .orElse(0);
    }

    public List<Double> notasDe(String disciplina) {
        return notas.containsKey(disciplina) ? List.copyOf(notas.get(disciplina)) : List.of();
    }

    public Iterator<String> disciplinas() {
        return List.copyOf(notas.keySet()).iterator();
    }
}
