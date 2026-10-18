package br.com.p2.exercises.shippingrules;

public class Pacote {
    private final double pesoKg;
    private final double distanciaKm;
    private final double valorDeclarado;

    public Pacote(double pesoKg, double distanciaKm, double valorDeclarado) {
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.valorDeclarado = valorDeclarado;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }
}
