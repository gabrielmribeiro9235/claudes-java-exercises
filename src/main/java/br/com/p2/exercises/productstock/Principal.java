package br.com.p2.exercises.productstock;

import java.time.LocalDate;
import java.util.Iterator;

public class Principal {
    static void main() {
        Produto a1 = new ProdutoPerecivel(String.format("LEI-%02d", 1), "Leite", 5.0, LocalDate.of(2026, 12, 1));
        Produto a2 = new ProdutoPerecivel(String.format("LEI-%02d", 1), "Leite", 5.0, LocalDate.of(2026, 12, 1));
        System.out.println("Mesmo produto? " + a1.equals(a2));

        Estoque estoque = new Estoque();
        estoque.adicionar(a1, 10);
        estoque.adicionar(a2, 5);
        System.out.println("Itens distintos = " + estoque.tiposDeProduto());
        System.out.println("Quantidade de leite = " + estoque.quantidadeDe(a1));

        estoque.adicionar(new ProdutoEletronico("MON-01", "Monitor", 900.0, 12), 0);
        estoque.adicionar(new ProdutoEletronico("TEC-01", "Teclado", 150.0, 6), 0);
        estoque.adicionar(new ProdutoEletronico("MOU-01", "Mouse", 80.0, 6), 3);
        estoque.removerEsgotados();
        System.out.println("Itens distintos apos remover = " + estoque.tiposDeProduto());
        System.out.println("Valor total = " + estoque.valorTotal());
        System.out.println("Frete total = " + estoque.freteTotal());
        System.out.println("Buscar MOU-01 = " + estoque.buscarPorCodigo("MOU-01"));
        System.out.println("Buscar XXX = " + estoque.buscarPorCodigo("XXX"));
        System.out.println("Mais baratos que 100 = " + estoque.maisBaratosQue(100));
        int n = 0;
        for (Iterator<Produto> it = estoque.iterador(); it.hasNext(); it.next()) n++;
        System.out.println("Percorridos = " + n);
    }
}
