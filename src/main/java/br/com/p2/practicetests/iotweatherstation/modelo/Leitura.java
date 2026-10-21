package br.com.p2.practicetests.iotweatherstation.modelo;

public class Leitura <T extends Comparable<T>> implements Comparable<Leitura<T>> {
    private final String sensorId;
    private final String tipo;
    private final T valor;
    private final int instante;

    public Leitura(String sensorId, String tipo, T valor, int instante) {
        this.sensorId = sensorId;
        this.tipo = tipo;
        this.valor = valor;
        this.instante = instante;
    }

    public String getSensorId() {
        return sensorId;
    }

    public T getValor() {
        return valor;
    }

    public String tipo() {
        return tipo;
    }

    @Override
    public int compareTo(Leitura<T> o) {
        if (this.valor.equals(o.valor)) {
            return Integer.compare(this.instante, o.instante);
        }

        return this.valor.compareTo(o.valor);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]=%s @t%d", tipo, sensorId, valor, instante);
    }
}
