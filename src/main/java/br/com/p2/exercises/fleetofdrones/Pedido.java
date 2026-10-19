package br.com.p2.exercises.fleetofdrones;

import java.util.Objects;

public class Pedido {
    private final String id;
    private final double pesoKg;
    private final double distanciaKm;

    public Pedido(String id, double pesoKg, double distanciaKm) {
        this.id = id;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }

    public String getId() {
        return id;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
