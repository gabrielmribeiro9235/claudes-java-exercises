package br.com.p2.exercises.bankaccount;

import java.util.List;

public class Principal {
    static void main() {
        Conta conta = new Conta("Ana", 100.0);
        String[] comandos = {"D:50", "S:30", "S:500", "D:abc", "X:10", "S:-5", "D:20:30", "S:40"};
        List<String> erros = Processador.processar(conta, comandos);
        System.out.println("Saldo final = " + conta.getSaldo());
        System.out.println("Processados = " + Processador.getComandosProcessados());
        System.out.println("Falhas = " + erros.size());
        for (String erro : erros) System.out.println(" - " + erro);
        Conta outra = new Conta("Bia", 0);
        try {
            conta.transferir(outra, 1000);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Transferencia recusada: faltam " + (e.getValorSolicitado() - e.getSaldoAtual()));
        } finally {
            System.out.println("Saldos: " + conta.getSaldo() + " / " + outra.getSaldo());
        }
    }
}
