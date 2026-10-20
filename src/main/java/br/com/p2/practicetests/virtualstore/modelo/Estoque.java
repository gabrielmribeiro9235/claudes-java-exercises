package br.com.p2.practicetests.virtualstore.modelo;

import java.util.*;

public class Estoque {
    private final Map<String, Integer> saldos;

    public Estoque() {
        saldos = new TreeMap<>();
    }

    public void repor(String sku, int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva" );
        }

        if (saldos.containsKey(sku)) {
            saldos.put(sku, saldos.get(sku) + qtd);
        } else {
            saldos.put(sku, qtd);
        }
    }

    public int disponivel(String sku) {
        if (!saldos.containsKey(sku)) {
            return 0;
        }

        return saldos.get(sku);
    }

    public boolean temSaldo(Produto p, int qtd) {
        Objects.requireNonNull(p, "O produto não pode ser nulo");
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva");
        }

        return !p.controlaEstoque() || (saldos.containsKey(p.getSku()) && saldos.get(p.getSku()) >= qtd);
    }

    public void baixar(Produto p, int qtd) throws EstoqueInsuficienteException {
        if (!p.controlaEstoque()) {
            return;
        }

        if (!saldos.containsKey(p.getSku())) {
            throw new IllegalArgumentException("O produto não está no estoque");
        }

        if (temSaldo(p, qtd)) {
            saldos.put(p.getSku(), saldos.get(p.getSku()) - qtd);
        } else {
            throw new EstoqueInsuficienteException(p.getSku(), qtd, saldos.get(p.getSku()));
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");

        saldos.forEach((key, value) ->
                joiner.add(String.format("%s = %d", key, value))
        );

        return "Estoque " + joiner;
    }
}
