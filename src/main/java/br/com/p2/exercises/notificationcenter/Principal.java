package br.com.p2.exercises.notificationcenter;

public class Principal {
    static void main() {
        Notificador push = new Notificador() {
            @Override
            public String canal() {
                return "PUSH";
            }

            @Override
            public boolean enviar(String destinatario, String mensagem) {
                System.out.println("[PUSH] " + destinatario + ": " + mensagem);
                return !destinatario.isEmpty();
            }
        };
        CentralNotificacoes central = new CentralNotificacoes(
                new NotificadorEmail(), new NotificadorSms(), new NotificadorConsole(), push);
        int a = central.enviarParaTodos("ana@ifsp.edu.br", "Prova na terca");
        System.out.println("Canais que enviaram: " + a);
        int b = central.enviarParaTodos("16999998888", "Prova na terca");
        System.out.println("Canais que enviaram: " + b);
        Notificador sms = central.buscarCanal("sms");
        System.out.println("buscarCanal(sms) = " + sms.canal());
        System.out.println("buscarCanal(fax) = " + central.buscarCanal("fax"));
        System.out.println("SMS invalido? " + sms.enviar("123", "oi"));
    }
}
