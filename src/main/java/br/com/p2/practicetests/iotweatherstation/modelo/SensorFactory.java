package br.com.p2.practicetests.iotweatherstation.modelo;

public final class SensorFactory {
    private SensorFactory() {}

    public static Sensor temperatura(String id, double ...amostras) {
        return new SensorTemperatura(id, amostras);
    }

    public static Sensor umidade(String id, double ...amostras) {
        return new SensorUmidade(id, amostras);
    }
}
