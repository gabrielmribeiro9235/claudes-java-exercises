package br.com.p2.exercises.productstock;

import java.util.HashMap;
import java.util.Iterator;
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

    Produto buscarPorCodigo(String codigo) {
        if (codigo == null) return null;

        return quantidades.keySet().stream().filter(p -> codigo.equals(p.getCodigo())).findFirst().orElse(null);
    }

    public void adicionar(Produto produto, int quantidade) {
        if (quantidades.containsKey(produto)) {
            int qtdAtual = quantidades.get(produto);
            quantidade += qtdAtual;
        }

        quantidades.put(produto, quantidade);
    }

    public List<Produto> maisBaratosQue(double limite) {
        return quantidades.keySet().stream().filter(p -> p.getPreco() < limite).toList();
    }

    public double freteTotal() {
        return quantidades.entrySet().stream().filter(e -> e.getValue() > 0).mapToDouble(e -> e.getKey().frete()).sum();
    }

    public Iterator<Produto> iterador() {
        return quantidades.keySet().iterator();
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