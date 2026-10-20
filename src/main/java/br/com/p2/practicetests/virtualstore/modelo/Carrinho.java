package br.com.p2.practicetests.virtualstore.modelo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Carrinho {
    private final Map<Produto, Integer> itens;

    public Carrinho() {
        itens = new LinkedHashMap<>();
    }

    public void adicionar(Produto p, int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva: " + qtd);
        }

        if (itens.containsKey(p)) {
            itens.replace(p, itens.get(p) + qtd);
        } else {
            itens.put(p, qtd);
        }
    }

    public void remover(Produto p, int qtd) {
        if (!itens.containsKey(p)) {
            throw new IllegalArgumentException(p + " nao esta no carrinho");
        }

        if (qtd < 0 || qtd > itens.get(p)) {
            throw new IllegalArgumentException("Nao e possivel remover " + qtd + " de " + p);
        }

        if (qtd == itens.get(p)) {
            itens.remove(p);
        } else {
            itens.replace(p, itens.get(p) - qtd);
        }
    }

    public int quantidadeDe(Produto p) {
        return itens.getOrDefault(p, 0);
    }

    public boolean isVazio() {
        return itens.isEmpty();
    }

    public double subtotal() {
        return isVazio() ?
                0 :
                itens.entrySet()
                        .stream()
                        .mapToDouble(e -> e.getKey().getPreco() * e.getValue())
                        .sum();
    }

    public double frete() {
        return isVazio() ?
                0 :
                itens.entrySet()
                        .stream()
                        .mapToDouble(e -> e.getKey().frete(e.getValue()))
                        .sum();
    }

    public Iterator<Produto> iterador() {
        return List.copyOf(itens.keySet()).iterator();
    }
}
