package br.com.p2.exercises.bankaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Processador {
    private static int comandosProcessados;

    private Processador() {}

    public static List<String> processar(Conta conta, String[] comandos) {
        Objects.requireNonNull(conta, "Conta não pode ser nula.");

        List<String> erros = new ArrayList<>();

        for (String comando : comandos) {
            try {
                String[] split = comando.split(":");
                if (split.length != 2) throw new RuntimeException("Formato invalido: " + comando);

                String tipo = split[0];

                if (!"D".equals(tipo) && !"S".equals(tipo)) throw new RuntimeException("Operacao desconhecida: " + tipo);

                int valor = Integer.parseInt(split[1]);

                if ("D".equals(tipo)) {
                    conta.depositar(valor);
                } else {
                    conta.sacar(valor);
                }
            } catch (NumberFormatException e) {
                erros.add("Numero invalido em " + "'" + comando + "'");
            } catch (RuntimeException | SaldoInsuficienteException e) {
                erros.add(e.getMessage());
            } finally {
                comandosProcessados++;
            }
        }

        return erros;
    }

    public static int getComandosProcessados() {
        return comandosProcessados;
    }
}
