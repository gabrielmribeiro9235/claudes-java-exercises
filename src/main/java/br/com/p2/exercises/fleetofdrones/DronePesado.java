package br.com.p2.exercises.fleetofdrones;

public class DronePesado extends Drone {
    public DronePesado(String id, int bateria) {
        super(id, bateria);
    }

    @Override
    protected double capacidadeKg() {
        return 10.0;
    }

    @Override
    protected double consumoPorKm() {
        return 2.5;
    }

    @Override
    protected double custoPorKm() {
        return 1.5;
    }
}
