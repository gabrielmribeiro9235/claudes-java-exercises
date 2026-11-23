package br.com.p3.exercises.productstock;

import java.sql.*;
import java.util.*;

public class ProductStock {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/productstock/stock.db";

    public void cadastrarCategoria(Categoria categoria) {
        Objects.requireNonNull(categoria, "Categoria não pode ser nula.");
        String sql = "INSERT INTO categoria (nome) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerCategoria(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        String sql = "DELETE FROM categoria WHERE nome = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Não é possível remover a categoria: " + nome + "\nRemova os produtos dela primeiro");
        }
    }

    public void atualizarCategoria(Categoria categoria) {
        Objects.requireNonNull(categoria, "Categoria não pode ser nula.");
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Categoria> getCategoria(int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Categoria(rs.getInt("id"), rs.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public List<Categoria> getCategorias() {
        String sql = "SELECT * FROM categoria";

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Categoria> categorias = new ArrayList<>();

            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("id"), rs.getString("nome")));
            }

            return categorias;
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarProduto(Produto produto) {
        Objects.requireNonNull(produto, "Produto não pode ser nula.");
        String sql = "INSERT INTO produto (nome, preco, quantidade, categoria_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getCategoria().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerProduto(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        String sql = "DELETE FROM produto WHERE nome = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> getProdutos() {
        String sql = "SELECT * FROM produto";

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Produto> produtos = new ArrayList<>();

            while (rs.next()) {
                Categoria categoria = getCategoria(rs.getInt("categoria_id"))
                        .orElseThrow(() -> new NoSuchElementException("Categoria do produto não existe"));
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        categoria
                ));
            }

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean atualizarEstoque(int idProduto, int qtd) {
        String sql = "UPDATE produto SET quantidade = quantidade - ? WHERE quantidade >= ? AND id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, qtd);
            stmt.setInt(2, qtd);
            stmt.setInt(3, idProduto);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String relatorioPorCategoria() {
        String sql = """
                SELECT c.nome AS nome_categoria, count(*) AS quantidade_produtos, SUM(p.preco * p.quantidade) AS valor_estoque
                    FROM categoria c JOIN produto p ON c.id = p.categoria_id
                    GROUP BY c.nome
                    ORDER BY valor_estoque DESC;
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            StringJoiner joiner = new StringJoiner("\n");

            while (rs.next()) {
                joiner.add(String.format(
                        "\nCategoria: %s\nQuantidade de produtos: %d\nValor em estoque: R$ %.2f",
                        rs.getString("nome_categoria"),
                        rs.getInt("quantidade_produtos"),
                        rs.getDouble("valor_estoque")
                ));
            }

            return joiner.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> produtosEmFalta(int minimo) {
        String sql = "SELECT * FROM produto WHERE quantidade < ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, minimo);

            ResultSet rs = stmt.executeQuery();

            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Categoria categoria = getCategoria(rs.getInt("categoria_id"))
                        .orElseThrow(() -> new NoSuchElementException("Categoria do produto não existe"));
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        categoria
                ));
            }

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
