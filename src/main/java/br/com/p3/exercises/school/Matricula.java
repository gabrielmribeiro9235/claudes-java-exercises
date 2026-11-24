package br.com.p3.exercises.school;

import java.util.Objects;

public class Matricula {
    private final Aluno aluno;
    private final Disciplina disciplina;
    private double nota1;
    private double nota2;

    public Matricula(Aluno aluno, Disciplina disciplina) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula.");
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public Matricula(Aluno aluno, Disciplina disciplina, double nota1, double nota2) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula.");
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        this.aluno = aluno;
        this.disciplina = disciplina;
        setNota1(nota1);
        setNota2(nota2);
    }

    public void setNota1(double nota1) {
        if (nota1 < 0 || nota1 > 10) {
            throw new IllegalArgumentException("A nota deve ser um número de 0 a 10");
        }
        this.nota1 = nota1;
    }

    public void setNota2(double nota2) {
        if (nota2 < 0 || nota2 > 10) {
            throw new IllegalArgumentException("A nota deve ser um número de 0 a 10");
        }
        this.nota2 = nota2;
    }

    public double getMedia() {
        return (nota1 + nota2) / 2;
    }

    public String getSituacao() {
        if (nota1 == 0 || nota2 == 0) {
            return "em andamento";
        }

        if (getMedia() >= 6.0) return "aprovado";

        if (getMedia() >= 4.0) return "recuperação";

        return "reprovado";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(aluno, matricula.aluno) && Objects.equals(disciplina, matricula.disciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aluno, disciplina);
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "aluno=" + aluno +
                ", disciplina=" + disciplina +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                '}';
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }
}
