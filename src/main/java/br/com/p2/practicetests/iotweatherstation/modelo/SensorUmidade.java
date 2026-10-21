package br.com.p2.practicetests.iotweatherstation.modelo;

final class SensorUmidade extends Sensor {
    public SensorUmidade(String id, double... amostras) {
        super(id, amostras);
    }

    @Override
    protected double minimo() {
        return 0.0;
    }

    @Override
    protected double maximo() {
        return 100.0;
    }

    @Override
    public String unidade() {
        return "%";
    }
}
