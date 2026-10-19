package br.com.p2.exercises.fleetofdrones;

public class DroneLeve extends Drone {
    public DroneLeve(String id, int bateria) {
        super(id, bateria);
    }

    @Override
    protected double capacidadeKg() {
        return 2.0;
    }

    @Override
    protected double consumoPorKm() {
        return 1.0;
    }

    @Override
    protected double custoPorKm() {
        return 0.5;
    }
}
