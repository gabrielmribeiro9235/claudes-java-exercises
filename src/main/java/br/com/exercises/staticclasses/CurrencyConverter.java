package br.com.exercises.staticclasses;

public class CurrencyConverter {
    private CurrencyConverter() {}

    public static double reaisToDolar(double reais, double exchangeRate) {
        if (reais < 0 || exchangeRate < 0) return 0;

        return reais / exchangeRate;
    }

    public static double dolarToReais(double dolar, double exchangeRate) {
        if (dolar < 0 || exchangeRate < 0) return 0;

        return dolar * exchangeRate;
    }
}
