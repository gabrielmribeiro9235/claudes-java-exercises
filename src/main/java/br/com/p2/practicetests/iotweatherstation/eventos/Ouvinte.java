package br.com.p2.practicetests.iotweatherstation.eventos;

import br.com.p2.practicetests.iotweatherstation.modelo.Leitura;

@FunctionalInterface
public interface Ouvinte {
    void aoReceber(Leitura<Double> leitura);
}
