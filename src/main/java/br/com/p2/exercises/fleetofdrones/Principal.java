package br.com.p2.exercises.fleetofdrones;

public class Principal {
    static void main() {
        Frota frota = new Frota();
        frota.adicionar(new DroneLeve("L1", 100));
        frota.adicionar(new DroneSolar("S1", 40));
        frota.adicionar(new DronePesado("P1", 100));
        try {
            frota.adicionar(new DroneLeve("L1", 50));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        frota.solicitar(new Pedido("A", 1.5, 10));
        frota.solicitar(new Pedido("B", 5.0, 20));
        frota.solicitar(new Pedido("C", 8.0, 5));
        frota.solicitar(new Pedido("D", 1.0, 15));
        frota.solicitar(new Pedido("E", 3.0, 1));

        System.out.println("== 1o processamento");
        System.out.print(frota.processarPendentes());
        System.out.println("Pendentes: " + frota.totalPendentes());
        System.out.println("Baterias: " + frota.dronesPorBateria());

        frota.recarregar("P1");
        System.out.println("== 2o processamento (apos recarregar P1)");
        System.out.print(frota.processarPendentes());
        System.out.println("Pendentes: " + frota.totalPendentes());
        System.out.println("Baterias: " + frota.dronesPorBateria());
    }
}
