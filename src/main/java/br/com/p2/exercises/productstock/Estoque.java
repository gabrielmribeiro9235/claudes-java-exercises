package br.com.p2.exercises.productstock;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> quantidades = new HashMap<>();

    public Map<Produto, Integer> getQuantidades() {
        return quantidades;
    }

    public void adicionar(Produto produto, int quantidade) {
        quantidades.put(produto, quantidade);
    }

    public void removerEsgotados() {
        for (Produto p : quantidades.keySet()) {
            if (quantidades.get(p) == 0) {
                quantidades.remove(p);
            }
        }
    }

    public double valorTotal() {
        double total = 0;
        for (Produto p : quantidades.keySet()) {
            total += p.getPreco() * quantidades.get(p);
        }
        return total;
    }
}