package br.com.p2.exercises.bankaccount;

import java.util.Locale;

public class SaldoInsuficienteException extends Exception {
    private final double saldoAtual;
    private final double valorSolicitado;

    public SaldoInsuficienteException(double saldoAtual, double valorSolicitado) {
        this.saldoAtual = saldoAtual;
        this.valorSolicitado = valorSolicitado;

        super(String.format(Locale.US, "Saldo insuficiente: saldo=%.1f, solicitado=%.1f", saldoAtual, valorSolicitado));
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public double getValorSolicitado() {
        return valorSolicitado;
    }
}
