package br.com.p2.practicetests.virtualstore.main;

import br.com.p2.practicetests.virtualstore.modelo.*;
import br.com.p2.practicetests.virtualstore.pagamento.Pagamento;
import br.com.p2.practicetests.virtualstore.pagamento.PagamentoFactory;
import br.com.p2.practicetests.virtualstore.servico.RepositorioMemoria;
import br.com.p2.practicetests.virtualstore.servico.ServicoPedido;

import java.util.Locale;

public class Principal {
    static void main() {
        Produto livro = new ProdutoFisico("LIV-1", "livro", 80, 0.5);
        Produto vasoFragil = new ProdutoFragil("VID-1", "vaso", 120, 2.0);
        Produto cursoDigital = new ProdutoDigital("CUR-1", "curso", 200);

        Estoque estoque = new Estoque();

        estoque.repor(livro.getSku(), 5);
        estoque.repor(vasoFragil.getSku(), 1);

        ServicoPedido servicoPedido = new ServicoPedido(new RepositorioMemoria<>(), estoque, pedido -> System.out.println("[NOTIFICACAO] " + pedido.getId() + " confirmado"));

        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(livro, 2);
        carrinho.adicionar(vasoFragil, 1);
        carrinho.adicionar(cursoDigital, 1);
        carrinho.adicionar(livro, 1);
        carrinho.remover(cursoDigital, 1);
        carrinho.adicionar(cursoDigital, 1);

        Produto outroLivro = new ProdutoFisico("LIV-1", "livro com mesmo sku", 100, 0.3);
        System.out.println("livros no carrinho = " + carrinho.quantidadeDe(outroLivro));

        System.out.printf(Locale.US, "subtotal=%.2f frete=%.2f\n", carrinho.subtotal(), carrinho.frete());

        String[] formas = {"pix", "cartao:6", "boleto", "cartao:abc", "cartao:20", "cripto"};

        for (String forma : formas) {
            try {
                Pagamento pagamento = PagamentoFactory.criar(forma);
                System.out.printf(Locale.US, "%-10s -> %s | total de 100.00 = %.2f\n",
                        forma,
                        pagamento.descricao(),
                        pagamento.total(100)
                );
            } catch (RuntimeException e) {
                System.out.println(forma + " -> erro: " + e.getMessage());
            }
        }

        try {
            Pedido pedido = servicoPedido.finalizar("P-001", carrinho, PagamentoFactory.criar("cartao:3"));
            System.out.println(pedido);
        } catch (EstoqueInsuficienteException e) {
            System.out.println(e.getMessage());
        }

        Carrinho novoCarrinho = new Carrinho();
        novoCarrinho.adicionar(livro, 1);
        novoCarrinho.adicionar(vasoFragil, 1);

        try {
            Pedido pedido = servicoPedido.finalizar("P-002", novoCarrinho, PagamentoFactory.criar("pix"));
            System.out.println(pedido);
        } catch (EstoqueInsuficienteException e) {
            System.out.println("Falha: " + e.getMessage());
        }

        System.out.println(estoque);

        System.out.println("Pedidos salvos = " + servicoPedido.quantidadeDePedidos());
        System.out.println("P-002 existe? " + servicoPedido.contemPedido("P-002"));

        try {
            Pedido pedido = servicoPedido.finalizar("P-003", new Carrinho(), PagamentoFactory.criar("pix"));
            System.out.println(pedido);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
