package br.com.p2.practicetests.academicsecretary.modelo;

public final class Professor extends Pessoa {
    private final String disciplina;

    public Professor(String nome, String prontuario, String disciplina) {
        super(nome, prontuario);
        this.disciplina = disciplina;
    }

    public void lancarNota(Aluno aluno, double nota) {
        aluno.lancarNota(disciplina, nota);
    }
}
