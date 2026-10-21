package br.com.p2.practicetests.iotweatherstation.modelo;

public abstract class Sensor {
    private final String id;
    private final double[] amostras;
    private int proxima;

    protected Sensor(String id, double ...amostras) {
        if (amostras.length == 0) {
            throw new IllegalArgumentException("Sensor sem amostras: " + id);
        }

        this.id = id;
        this.amostras = amostras;
    }

    protected abstract double minimo();

    protected abstract double maximo();

    public abstract String unidade();

    public String id() {
        return id;
    }

    public String tipo() {
        return getClass().getSimpleName();
    }

    public final Leitura<Double> coletar(int instante) {
        double valor = amostras[proxima];
        proxima = (proxima + 1) % amostras.length;

        if (valor > maximo() || valor < minimo()) {
            throw new LeituraInvalidaException("Leitura invalida de " + id + ": " + valor);
        }

        return new Leitura<>(id, tipo(), valor, instante);
    }
}
