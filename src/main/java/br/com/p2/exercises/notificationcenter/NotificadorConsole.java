package br.com.p2.exercises.notificationcenter;

public class NotificadorConsole implements Notificador {
    @Override
    public String canal() {
        return "CONSOLE";
    }

    @Override
    public boolean enviar(String destinatario, String mensagem) {
        System.out.println("[CONSOLE] " + destinatario + " <- " + mensagem);

        return true;
    }
}
