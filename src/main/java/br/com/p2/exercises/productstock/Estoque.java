package br.com.p2.exercises.productstock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {
    private final Map<Produto, Integer> quantidades = new HashMap<>();

    public int quantidadeDe(Produto p) {
        return quantidades.get(p);
    }

    public int tiposDeProdutos() {
        return quantidades.size();
    }

    public void adicionar(Produto produto, int quantidade) {
        if (quantidades.containsKey(produto)) {
            int qtdAtual = quantidades.get(produto);
            quantidade += qtdAtual;
        }

        quantidades.put(produto, quantidade);
    }

    public void removerEsgotados() {
        List<Produto> produtosZerados = quantidades.keySet().stream().filter(q -> quantidades.get(q) == 0).toList();

        for (Produto produto : produtosZerados) {
            quantidades.remove(produto);
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