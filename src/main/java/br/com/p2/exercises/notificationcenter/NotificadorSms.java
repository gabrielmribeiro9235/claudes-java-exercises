package br.com.p2.exercises.notificationcenter;

public class NotificadorSms implements Notificador {
    @Override
    public String canal() {
        return "SMS";
    }

    @Override
    public boolean enviar(String destinatario, String mensagem) {
        if (destinatario == null || destinatario.length() != 11) return false;

        for (char c : destinatario.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        System.out.println("[SMS] para " + destinatario + ": " + mensagem);

        return true;
    }
}
