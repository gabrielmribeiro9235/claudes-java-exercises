package br.com.p2.exercises.shippingrules;

public class FretePadrao implements RegraFrete {
    @Override
    public double calcular(Pacote pacote) {
        return pacote.getPesoKg() * 2.0 + pacote.getDistanciaKm() * 0.05;
    }
}
