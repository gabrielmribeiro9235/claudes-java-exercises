package br.com.p2.exercises.library;

public abstract class Item implements Emprestavel {
    private final String codigo;
    private final String titulo;
    private boolean emprestado;

    public Item(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    protected void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public double calcularMulta(int diasComItem) {
        return Math.max((diasComItem - prazoDias()) * multaPorDia(), 0);
    }

    @Override
    public String toString() {
        return String.format("%s[%s - %s]", getClass().getSimpleName(), codigo, titulo);
    }
}
