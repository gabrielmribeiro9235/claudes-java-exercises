package br.com.p2.practicetests.iotweatherstation.modelo;

final class SensorTemperatura extends Sensor {
    public SensorTemperatura(String id, double... amostras) {
        super(id, amostras);
    }

    @Override
    protected double minimo() {
        return -50.0;
    }

    @Override
    protected double maximo() {
        return 60.0;
    }

    @Override
    public String unidade() {
        return "C";
    }
}
