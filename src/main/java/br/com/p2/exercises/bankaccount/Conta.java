package br.com.p2.exercises.bankaccount;

import java.util.Objects;

public class Conta {
    private final String titular;
    private double saldo;

    public Conta(String titular, double saldo) {
        if (titular == null || titular.isBlank()) throw new IllegalArgumentException("Titular não pode ser nulo ou branco.");

        if (saldo < 0) throw new IllegalArgumentException("O saldo inicial deve ser positivo: " + saldo);

        this.titular = titular;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void depositar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor de deposito deve ser positivo: " + valor);

        saldo += valor;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) throw new IllegalArgumentException("Valor de saque deve ser positivo: " + valor);

        if (valor > saldo) throw new SaldoInsuficienteException(saldo, valor);

        saldo -= valor;
    }

    public void transferir(Conta destino, double valor) throws SaldoInsuficienteException {
        Objects.requireNonNull(destino, "A conta de destino não pode ser nula.");

        try {
            sacar(valor);
            destino.depositar(valor);
        } catch (SaldoInsuficienteException e) {
            throw new SaldoInsuficienteException(saldo, valor);
        }
    }
}
