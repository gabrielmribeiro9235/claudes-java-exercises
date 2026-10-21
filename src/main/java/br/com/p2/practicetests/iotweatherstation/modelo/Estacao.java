package br.com.p2.practicetests.iotweatherstation.modelo;

import br.com.p2.practicetests.iotweatherstation.eventos.Barramento;

import java.util.*;

public class Estacao {
    private final Map<String, Sensor> sensores;
    private final Map<String, Janela<Double>> janelas;
    private final Map<String, List<Leitura<Double>>> historico;
    private final List<String> ocorrencias;
    private final Barramento barramento;
    private int leiturasInvalidas;
    private int instante = 1;

    public Estacao(Barramento barramento) {
        this.barramento = barramento;

        sensores = new TreeMap<>();
        janelas = new HashMap<>();
        historico = new HashMap<>();
        ocorrencias = new ArrayList<>();
    }

    public void registrar(Sensor sensor) {
        if (sensores.containsKey(sensor.id())) {
            throw new IllegalArgumentException("Sensor repetido: " + sensor.id());
        }
        sensores.put(sensor.id(), sensor);

        janelas.put(sensor.id(), new Janela<>(3));

        historico.put(sensor.id(), new ArrayList<>());
    }

    public int ciclo() {
        int validas = 0;

        for (Sensor s : sensores.values()) {
            try {
                Leitura<Double> coleta = s.coletar(instante);
                janelas.get(s.id()).adicionar(coleta.getValor());
                historico.get(s.id()).add(coleta);
                barramento.publicar(coleta);
                validas++;
            } catch (RuntimeException e) {
                leiturasInvalidas++;
                ocorrencias.add(String.format("t%d: %s", instante, e.getMessage()));
            }
        }

        instante++;

        return validas;
    }

    public double mediaMovel(String idSensor) {
        if (!sensores.containsKey(idSensor)) {
            throw new IllegalArgumentException("Sensor desconhecido: " + idSensor);
        }

        Janela<Double> janela = janelas.get(idSensor);

        return janela.media();
    }

    public Leitura<Double> maiorLeitura(String idSensor) {
        if (!sensores.containsKey(idSensor)) {
            throw new IllegalArgumentException("Sensor desconhecido: " + idSensor);
        }

        return Estatisticas.maximo(historico.get(idSensor));
    }

    public Map<String, Double> mediasGerais() {
        Map<String, Double> medias = new TreeMap<>();

        historico.forEach((key, value) -> {
            double media = value.stream().mapToDouble(Leitura::getValor).average().orElse(0.0);
            medias.put(key, media);
        });

        return medias;
    }

    public int getLeiturasInvalidas() {
        return leiturasInvalidas;
    }

    public List<String> getOcorrencias() {
        return List.copyOf(ocorrencias);
    }
}
