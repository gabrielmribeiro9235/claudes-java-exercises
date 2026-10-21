package br.com.p2.practicetests.iotweatherstation.main;

import br.com.p2.practicetests.iotweatherstation.eventos.Barramento;
import br.com.p2.practicetests.iotweatherstation.modelo.Estacao;
import br.com.p2.practicetests.iotweatherstation.modelo.Sensor;
import br.com.p2.practicetests.iotweatherstation.modelo.SensorFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Principal {
    static final int CICLOS = 6;
    static void main() {
        Barramento barramento = new Barramento();
        Estacao estacao = new Estacao(barramento);

        Sensor sensorTemperatura = SensorFactory.temperatura("T1", 22.0, 25.5, 31.0, 36.5, 99.0, 28.0);
        Sensor sensorUmidade = SensorFactory.umidade("U1", 40, 55, 70, 105, 60, 45);

        estacao.registrar(sensorTemperatura);
        estacao.registrar(sensorUmidade);

        barramento.registrarParaTodos(leitura ->
                System.out.println("LOG\t" + leitura)
        );
        barramento.registrar(sensorTemperatura.tipo(), leitura -> {
                    if (leitura.getValor() > 35.0) {
                        System.out.printf(Locale.US, "ALERTA calor: %.1f\n", leitura.getValor());
                    }
                }
        );
        barramento.registrar(sensorTemperatura.tipo(), leitura -> {
            if (leitura.getValor().equals(31.0)) {
                throw new IllegalStateException("ouvinte com defeito");
            }
        });

        final Map<String, Integer> numeroDeLeituras = new HashMap<>();
        barramento.registrar(sensorUmidade.tipo(), leitura -> {
            if (numeroDeLeituras.containsKey(leitura.getSensorId())) {
                numeroDeLeituras.put(leitura.getSensorId(), numeroDeLeituras.get(leitura.getSensorId()) + 1);
            } else {
                numeroDeLeituras.put(leitura.getSensorId(), 1);
            }
        });

        for (int i = 0; i < CICLOS; i++) {
            int validas = estacao.ciclo();

            System.out.printf("-- ciclo %d: %d leitura(s) valida(s)\n", i + 1, validas);
        }

        System.out.printf(Locale.US, "Media movel T1 = %.2f\n", estacao.mediaMovel(sensorTemperatura.id()));
        System.out.printf(Locale.US, "Media movel U1 = %.2f\n", estacao.mediaMovel(sensorUmidade.id()));

        System.out.printf("Maior T1 = %s\n", estacao.maiorLeitura(sensorTemperatura.id()));
        System.out.printf("Maior U1 = %s\n", estacao.maiorLeitura(sensorUmidade.id()));

        Map<String, Double> mediasGerais = estacao.mediasGerais();
        System.out.printf(Locale.US, "Media geral T1 = %.2f\n", mediasGerais.get(sensorTemperatura.id()));
        System.out.printf(Locale.US, "Media geral U1 = %.2f\n", mediasGerais.get(sensorUmidade.id()));

        System.out.printf("Invalidas = %d %s\n", estacao.getLeiturasInvalidas(), estacao.getOcorrencias());

        System.out.println("Falhas de ouvintes = " + barramento.getFalhasDeOuvintes());

        System.out.println("Contagem umidade = " + numeroDeLeituras);

        try {
            System.out.printf(Locale.US, "Media movel X9 = %.2f\n", estacao.mediaMovel("X9"));
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
