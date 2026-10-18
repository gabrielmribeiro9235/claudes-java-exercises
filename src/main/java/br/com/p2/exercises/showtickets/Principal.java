package br.com.p2.exercises.showtickets;

import java.util.Locale;

public class Principal {
    static void main() {
        Ingresso[] ingressos = {
                new Ingresso("Rock in Rio", 300.0),
                new IngressoMeia("Rock in Rio", 300.0, "RG-1234"),
                new IngressoVip("Rock in Rio", 300.0, 50.0),
                new IngressoCamarote("Rock in Rio", 300.0, 50.0)
        };
        double total = 0;
        for (Ingresso ingresso : ingressos) {
            System.out.println(ingresso);
            System.out.printf(Locale.US, " valor final = %.2f%n", ingresso.valorFinal());
            total += ingresso.valorFinal();
        }
        System.out.printf(Locale.US, "Total = %.2f%n", total);
    }
}
