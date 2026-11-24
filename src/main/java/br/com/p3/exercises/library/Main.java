
package br.com.p3.exercises.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/library/library.db?foreign_keys=on";

    public static void main(String[] args) {
        DatabaseInitializer.init();
        Library library = new Library();

        Livro cleanCode = new Livro("Clean Code", 5);
        Livro effectiveJava = new Livro("Effective Java", 4);
        Livro cProgramming = new Livro("The C Programming Language", 3);
        Livro domainDrivenDesign = new Livro("Domain-Driven Design", 2);
        Livro algorithms = new Livro("Introduction to Algorithms", 2);
        Livro refactoring = new Livro("Refactoring", 2);
        Livro dataStructures = new Livro("Data Structures", 2);
        Livro semEstoque = new Livro("Livro sem estoque", 0);

        Usuario ana = new Usuario("Ana", false);
        Usuario bruno = new Usuario("Bruno", false);
        Usuario carla = new Usuario("Carla", false);
        Usuario diego = new Usuario("Diego", false);
        Usuario bloqueado = new Usuario("Usuário bloqueado", true);
        Usuario limite = new Usuario("Limite de empréstimos", false);

        System.out.println("=== CADASTRO DE LIVROS ===");

        library.adicionarLivro(cleanCode);
        library.adicionarLivro(effectiveJava);
        library.adicionarLivro(cProgramming);
        library.adicionarLivro(domainDrivenDesign);
        library.adicionarLivro(algorithms);
        library.adicionarLivro(refactoring);
        library.adicionarLivro(dataStructures);
        library.adicionarLivro(semEstoque);

        System.out.println("Livros cadastrados com sucesso.");

        System.out.println("\n=== CADASTRO DE USUÁRIOS ===");

        library.adicionarUsuario(ana);
        library.adicionarUsuario(bruno);
        library.adicionarUsuario(carla);
        library.adicionarUsuario(diego);
        library.adicionarUsuario(bloqueado);
        library.adicionarUsuario(limite);

        System.out.println("Usuários cadastrados com sucesso.");

        int cleanCodeId = buscarId("livro", "titulo", cleanCode.getTitulo());
        int effectiveJavaId = buscarId("livro", "titulo", effectiveJava.getTitulo());
        int cProgrammingId = buscarId("livro", "titulo", cProgramming.getTitulo());
        int domainDrivenDesignId = buscarId("livro", "titulo", domainDrivenDesign.getTitulo());
        int algorithmsId = buscarId("livro", "titulo", algorithms.getTitulo());
        int refactoringId = buscarId("livro", "titulo", refactoring.getTitulo());
        int dataStructuresId = buscarId("livro", "titulo", dataStructures.getTitulo());
        int semEstoqueId = buscarId("livro", "titulo", semEstoque.getTitulo());

        int anaId = buscarId("usuario", "nome", ana.getNome());
        int brunoId = buscarId("usuario", "nome", bruno.getNome());
        int carlaId = buscarId("usuario", "nome", carla.getNome());
        int diegoId = buscarId("usuario", "nome", diego.getNome());
        int bloqueadoId = buscarId("usuario", "nome", bloqueado.getNome());
        int limiteId = buscarId("usuario", "nome", limite.getNome());

        System.out.println("\n=== EMPRÉSTIMOS ===");

        library.emprestar(cleanCodeId, anaId);
        library.emprestar(effectiveJavaId, anaId);
        library.emprestar(cProgrammingId, anaId);

        library.emprestar(cleanCodeId, brunoId);
        library.emprestar(effectiveJavaId, brunoId);
        library.emprestar(domainDrivenDesignId, brunoId);

        library.emprestar(cleanCodeId, carlaId);
        library.emprestar(algorithmsId, carlaId);

        library.emprestar(cProgrammingId, diegoId);
        library.emprestar(refactoringId, diegoId);

        System.out.println("Empréstimos realizados com sucesso.");

        System.out.println("\n=== LIMITE DE EMPRÉSTIMOS ===");

        library.emprestar(domainDrivenDesignId, limiteId);
        library.emprestar(algorithmsId, limiteId);
        library.emprestar(refactoringId, limiteId);

        try {
            library.emprestar(dataStructuresId, limiteId);
            System.out.println("ERRO: o usuário conseguiu realizar um quarto empréstimo.");
        } catch (RuntimeException e) {
            System.out.println("Quarto empréstimo recusado: " + e.getMessage());
        }

        System.out.println("\n=== USUÁRIO BLOQUEADO ===");

        try {
            library.emprestar(cleanCodeId, bloqueadoId);
            System.out.println("ERRO: o usuário bloqueado conseguiu realizar um empréstimo.");
        } catch (RuntimeException e) {
            System.out.println("Empréstimo recusado: " + e.getMessage());
        }

        System.out.println("\n=== LIVRO SEM ESTOQUE ===");

        try {
            library.emprestar(semEstoqueId, anaId);
            System.out.println("ERRO: o empréstimo foi realizado sem exemplares disponíveis.");
        } catch (RuntimeException e) {
            System.out.println("Empréstimo recusado: " + e.getMessage());
        }

        int emprestimoAtrasadoId = buscarEmprestimoId(cleanCodeId, anaId);

        alterarDataPrevista(
                emprestimoAtrasadoId,
                LocalDate.now().minusDays(5)
        );

        System.out.println("\n=== RELATÓRIO DE EMPRÉSTIMOS EM ATRASO ===");

        List<String> atrasados = library.atrasados();

        for (String atraso : atrasados) {
            System.out.println(atraso);
        }

        System.out.println("\n=== DEVOLUÇÃO COM ATRASO ===");

        double multa = library.devolver(emprestimoAtrasadoId);

        System.out.printf("Multa aplicada: R$ %.2f%n", multa);
        System.out.println("Usuário após devolução: " + library.getUsuario(anaId));

        System.out.println("\n=== DEVOLUÇÃO SEM ATRASO ===");

        int emprestimoNoPrazoId = buscarEmprestimoId(domainDrivenDesignId, brunoId);

        double multaNoPrazo = library.devolver(emprestimoNoPrazoId);

        System.out.printf("Multa aplicada: R$ %.2f%n", multaNoPrazo);

        System.out.println("\n=== RELATÓRIO APÓS DEVOLUÇÕES ===");

        atrasados = library.atrasados();

        for (String atraso : atrasados) {
            System.out.println(atraso);
        }

        System.out.println("\n=== TOP 5 LIVROS MAIS EMPRESTADOS ===");

        List<Livro> maisEmprestados = library.livrosMaisEmprestados();

        for (int i = 0; i < maisEmprestados.size(); i++) {
            System.out.printf("%dº lugar: %s%n", i + 1, maisEmprestados.get(i));
        }

        System.out.println("\n=== ESTOQUE APÓS DEVOLUÇÕES ===");

        System.out.println(library.getLivro(cleanCodeId));
        System.out.println(library.getLivro(domainDrivenDesignId));
    }

    private static int buscarId(String tabela, String coluna, String valor) {
        String sql = "SELECT id FROM " + tabela + " WHERE " + coluna + " = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, valor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

            throw new IllegalStateException("Registro não encontrado: " + valor);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int buscarEmprestimoId(int livroId, int usuarioId) {
        String sql = """
                SELECT id
                FROM emprestimo
                WHERE livro_id = ? AND usuario_id = ?
                ORDER BY id DESC
                LIMIT 1
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, livroId);
            stmt.setInt(2, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

            throw new IllegalStateException("Empréstimo não encontrado.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void alterarDataPrevista(int emprestimoId, LocalDate dataPrevista) {
        String sql = "UPDATE emprestimo SET data_prevista = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, dataPrevista.toString());
            stmt.setInt(2, emprestimoId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
