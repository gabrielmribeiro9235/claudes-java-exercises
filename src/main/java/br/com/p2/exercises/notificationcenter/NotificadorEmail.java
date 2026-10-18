package br.com.p2.exercises.notificationcenter;

public class NotificadorEmail implements Notificador {
    @Override
    public String canal() {
        return "EMAIL";
    }

    @Override
    public boolean enviar(String destinatario, String mensagem) {
        if (destinatario == null || !destinatario.contains("@")) {
            return false;
        }

        System.out.println("[EMAIL] para " + destinatario + ": " + mensagem);

        return true;
    }
}
