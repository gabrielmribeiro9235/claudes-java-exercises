package br.com.p2.exercises.fleetofdrones;

import java.util.Objects;

public abstract class Drone implements Entregador, Comparable<Drone> {
    private final String id;
    protected int bateria;

    public Drone(String id, int bateria) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id não pode ser nulo ou vazio.");
        }

        if (bateria < 0 || bateria > 100) {
            throw new IllegalArgumentException("Bateria deve estar entre 0 e 100");
        }

        this.id = id;
        this.bateria = bateria;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public boolean podeEntregar(Pedido pedido) {
        Objects.requireNonNull(pedido, "Pedido não pode ser nulo.");

        return bateria >= consumoPorKm() * pedido.getDistanciaKm() * 2 && capacidadeKg() >= pedido.getPesoKg();
    }

    @Override
    public double custo(Pedido pedido) {
        Objects.requireNonNull(pedido, "Pedido não pode ser nulo.");

        return custoPorKm() * pedido.getDistanciaKm() * 2;
    }

    protected abstract double capacidadeKg();

    protected abstract double consumoPorKm();

    protected abstract double custoPorKm();

    public void entregar(Pedido pedido) {
        if (!podeEntregar(pedido)) {
            throw new IllegalStateException("Drone " + id + " não pode entregar o pedido.");
        }

        bateria -= (int) Math.ceil(pedido.getDistanciaKm() * consumoPorKm() * 2);
    }

    public void recarregar() {
        bateria = 100;
    }

    @Override
    public int compareTo(Drone o) {
        if (bateria == o.bateria) {
            return this.id.compareTo(o.id);
        }

        return Integer.compare(o.bateria, this.bateria);
    }

    @Override
    public String toString() {
        return String.format("%s[%s, bateria=%d]",
                getClass().getSimpleName(),
                id,
                bateria
        );
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Drone drone)) return false;
        return Objects.equals(id, drone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
