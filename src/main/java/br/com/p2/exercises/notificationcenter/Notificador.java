package br.com.p2.exercises.notificationcenter;

public interface Notificador {
    String canal();
    boolean enviar(String destinatario, String mensagem);
}
