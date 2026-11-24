package br.com.p3.exercises.library;

import java.util.Objects;

public class Usuario {
    private static int ID_ATUAL = 1;

    private final int id;
    private final String nome;
    private boolean bloqueado;

    public Usuario(String nome, boolean bloqueado) {
        this(ID_ATUAL++, nome, bloqueado);
    }

    public Usuario(int id, String nome, boolean bloqueado) {
        this.id = id;
        this.nome = nome;
        this.bloqueado = bloqueado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", bloqueado=" + bloqueado +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
