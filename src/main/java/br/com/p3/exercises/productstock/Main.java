package br.com.p3.exercises.productstock;

public class Main {
    static void main() {
        DatabaseInitializer.init();
        ProductStock productStock = new ProductStock();

        Categoria eletronicos = new Categoria("Eletrônicos");
        Categoria alimentos = new Categoria("Alimentos");
        Categoria papelaria = new Categoria("Papelaria");

        productStock.cadastrarCategoria(eletronicos);
        productStock.cadastrarCategoria(alimentos);
        productStock.cadastrarCategoria(papelaria);

        String nomeEletronicos = eletronicos.getNome();
        String nomeAlimentos = alimentos.getNome();
        String nomePapelaria = papelaria.getNome();

        eletronicos = productStock.getCategorias().stream()
                .filter(c -> c.getNome().equals(nomeEletronicos))
                .findFirst()
                .orElseThrow();

        alimentos = productStock.getCategorias().stream()
                .filter(c -> c.getNome().equals(nomeAlimentos))
                .findFirst()
                .orElseThrow();

        papelaria = productStock.getCategorias().stream()
                .filter(c -> c.getNome().equals(nomePapelaria))
                .findFirst()
                .orElseThrow();

        Produto notebook = new Produto("Notebook", 3500.00, 10, eletronicos);
        Produto mouse = new Produto("Mouse", 80.00, 25, eletronicos);
        Produto arroz = new Produto("Arroz", 25.00, 50, alimentos);
        Produto feijao = new Produto("Feijão", 8.50, 0, alimentos);
        Produto caderno = new Produto("Caderno", 20.00, 5, papelaria);

        productStock.cadastrarProduto(notebook);
        productStock.cadastrarProduto(mouse);
        productStock.cadastrarProduto(arroz);
        productStock.cadastrarProduto(feijao);
        productStock.cadastrarProduto(caderno);

        System.out.println("=== PRODUTOS CADASTRADOS ===");
        productStock.getProdutos().forEach(System.out::println);

        System.out.println("\n=== BAIXA DE ESTOQUE ===");

        int idNotebook = productStock.getProdutos().stream()
                .filter(p -> p.getNome().equals("Notebook"))
                .findFirst()
                .orElseThrow()
                .getId();

        int idFeijao = productStock.getProdutos().stream()
                .filter(p -> p.getNome().equals("Feijão"))
                .findFirst()
                .orElseThrow()
                .getId();

        int idMouse = productStock.getProdutos().stream()
                .filter(p -> p.getNome().equals("Mouse"))
                .findFirst()
                .orElseThrow()
                .getId();

        System.out.println("Baixa de 3 notebooks: "
                + productStock.atualizarEstoque(idNotebook, 3));

        System.out.println("Baixa de 1 feijão: "
                + productStock.atualizarEstoque(idFeijao, 1));

        System.out.println("Baixa de 100 mouses: "
                + productStock.atualizarEstoque(idMouse, 100));

        System.out.println("\n=== PRODUTOS APÓS BAIXA ===");
        productStock.getProdutos().forEach(System.out::println);

        System.out.println("\n=== RELATÓRIO POR CATEGORIA ===");
        System.out.println(productStock.relatorioPorCategoria());

        System.out.println("\n=== PRODUTOS EM FALTA ===");
        productStock.produtosEmFalta(10).forEach(System.out::println);

        System.out.println("\n=== REMOÇÃO DE CATEGORIAS ===");

        productStock.removerCategoria("Eletrônicos");

        productStock.removerCategoria("Papelaria");

        System.out.println("\n=== CATEGORIAS RESTANTES ===");
        productStock.getCategorias().forEach(System.out::println);
    }
}