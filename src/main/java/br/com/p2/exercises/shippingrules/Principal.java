package br.com.p2.exercises.shippingrules;

import java.util.Locale;

public class Principal {
    static void main() {
        Pacote leve = new Pacote(2.0, 100.0, 80.0);
        Pacote caro = new Pacote(5.0, 400.0, 350.0);

        String[] tipos = {"padrao", "expresso", "gratis200", "retirada"};
        for (String tipo : tipos) {
            Pedido a = new Pedido(leve, FreteFactory.para(tipo));
            Pedido b = new Pedido(caro, FreteFactory.para(tipo));
            System.out.printf(Locale.US, "%-10s leve: frete=%.2f total=%.2f | caro: frete=%.2f total=%.2f%n",
                    tipo, a.frete(), a.total(), b.frete(), b.total());
        }
        
        RegraFrete promo = p -> Math.min(10.0, new FretePadrao().calcular(p));
        System.out.printf(Locale.US, "promo caro: %.2f%n", new Pedido(caro, promo).frete());
        RegraFrete combinada = new FreteExpresso(new FreteGratisAcimaDe(300.0, promo));
        System.out.printf(Locale.US, "combinada leve: %.2f | caro: %.2f%n",
                combinada.calcular(leve), combinada.calcular(caro));
        try {
            FreteFactory.para("drone");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
