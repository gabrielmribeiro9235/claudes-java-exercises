package br.com.p2.practicetests.virtualstore.servico;

import br.com.p2.practicetests.virtualstore.modelo.*;
import br.com.p2.practicetests.virtualstore.pagamento.Pagamento;

import java.util.Iterator;

public class ServicoPedido {
    private final Repositorio<String, Pedido> repositorio;
    private final Estoque estoque;
    private final Notificador notificador;

    public ServicoPedido(Repositorio<String, Pedido> repositorio, Estoque estoque, Notificador notificador) {
        this.repositorio = repositorio;
        this.estoque = estoque;
        this.notificador = notificador;
    }

    public Pedido finalizar(String id, Carrinho carrinho, Pagamento pagamento) throws EstoqueInsuficienteException {
        if (carrinho.isVazio()) {
            throw new IllegalStateException("Carrinho vazio");
        }

        if (repositorio.buscar(id) != null) {
            throw new IllegalArgumentException("Pedido ja existe: " + id);
        }

        Iterator<Produto> produtos =  carrinho.iterador();
        while (produtos.hasNext()) {
            Produto produto = produtos.next();

            int solicitado = carrinho.quantidadeDe(produto);

            if (!estoque.temSaldo(produto, solicitado)) {
                throw new EstoqueInsuficienteException(produto.getSku(), solicitado, estoque.disponivel(produto.getSku()));
            }
        }

        produtos = carrinho.iterador();
        while (produtos.hasNext()) {
            Produto produto = produtos.next();
            estoque.baixar(produto, carrinho.quantidadeDe(produto));
        }

        Pedido pedido = new Pedido(
                id,
                pagamento.descricao(),
                carrinho.subtotal() + carrinho.frete(),
                pagamento.total(carrinho.subtotal() + carrinho.frete())
        );

        repositorio.salvar(id, pedido);
        notificador.notificar(pedido);
        return pedido;
    }

    public int quantidadeDePedidos() {
        return repositorio.listar().size();
    }

    public boolean contemPedido(String id) {
        return repositorio.buscar(id) != null;
    }
}
