package br.com.p2.practicetests.iotweatherstation.modelo;

public class LeituraInvalidaException extends RuntimeException {
    public LeituraInvalidaException(String message) {
        super(message);
    }
}
