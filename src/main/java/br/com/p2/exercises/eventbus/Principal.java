package br.com.p2.exercises.eventbus;

public class Principal {
    private static double totalVendas = 0.0;

    static void main() {
        Barramento barramento = new Barramento();
        ContadorEventos contador = new ContadorEventos();

        barramento.registrarParaTodos(contador);
        barramento.registrarParaTodos(e -> System.out.println("LOG -> " + e));
        barramento.registrar("EventoCompra", e -> {
            EventoCompra compra = (EventoCompra) e;
            totalVendas += compra.getValor();
        });
        barramento.registrar("EventoErro", e -> {
            if (e instanceof EventoErro erro && erro.getCodigo() >= 500) {
                throw new IllegalStateException("alerta critico " + erro.getCodigo());
            }
        });
        barramento.registrar("EventoErro", new Ouvinte() {
            private int vistos = 0;

            @Override
            public void aoOcorrer(Evento evento) {
                vistos++;
                System.out.println("ERRO -> ja vi " + vistos + " erro(s)");
            }
        });

        System.out.println("notificados = " + barramento.publicar(new EventoLogin("ana")));
        System.out.println("notificados = " + barramento.publicar(new EventoCompra("ana", 120.5)));
        System.out.println("notificados = " + barramento.publicar(new EventoCompra("bia", 79.5)));
        System.out.println("notificados = " + barramento.publicar(new EventoErro(404, "pagina nao encontrada")));
        System.out.println("notificados = " + barramento.publicar(new EventoErro(500, "falha interna")));

        Barramento vazio = new Barramento();
        System.out.println("sem ouvintes = " + vazio.publicar(new EventoLogin("caio")));

        System.out.println("Total de vendas = " + totalVendas);
        System.out.println("Contagem = " + contador.getContagem());
    }
}
