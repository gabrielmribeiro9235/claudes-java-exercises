package br.com.p2.exercises.notificationcenter;

import java.util.Arrays;

public class CentralNotificacoes {
    private final Notificador[] notificadores;

    public CentralNotificacoes(Notificador... notificadores) {
        this.notificadores = notificadores;
    }

    int enviarParaTodos(String destinatario, String mensagem) {
        int contador = 0;

        for (Notificador notificador : notificadores) {
            if (notificador.enviar(destinatario, mensagem)) contador++;
        }

        return contador;
    }

    Notificador buscarCanal(String nome) {
        return Arrays.stream(notificadores).filter(n -> nome.equalsIgnoreCase(n.canal())).findFirst().orElse(null);
    }
}
