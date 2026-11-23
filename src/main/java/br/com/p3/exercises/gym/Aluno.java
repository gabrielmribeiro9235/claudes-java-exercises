package br.com.p3.exercises.gym;

import java.time.LocalDate;
import java.util.Objects;

public class Aluno {
    private static int ID_ATUAL = 1;
    private final int id;
    private final String nome;
    private final LocalDate dataNascimento;
    private boolean ativo;
    private Plano plano;

    public Aluno(String nome, LocalDate dataNascimento) {
        this.id = ID_ATUAL++;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Aluno(int id, String nome, LocalDate dataNascimento, boolean ativo, Plano plano) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
        this.plano = plano;
    }

    public void cadastrarPlano(Plano plano) {
        if (this.plano != null && ativo) {
            throw new IllegalStateException("Aluno já tem um plano cadastrado");
        }
        this.plano = plano;
        ativo = true;
    }

    public void alterarPlano(Plano plano) {
        if (this.plano == null || !ativo) {
            cadastrarPlano(plano);
        } else {
            this.plano = plano;
        }
    }

    public void desativarAluno() {
        if (!ativo) {
            throw new IllegalStateException("Aluno já está desativado");
        }
        ativo = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return id == aluno.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", ativo=" + ativo +
                ", plano=" + '\'' + plano.getNome() + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Plano getPlano() {
        return plano;
    }
}
