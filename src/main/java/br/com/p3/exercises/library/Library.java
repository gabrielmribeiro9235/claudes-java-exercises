package br.com.p3.exercises.library;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Library {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/library/library.db?foreign_keys=on";

    public void adicionarLivro(Livro livro) {
        Objects.requireNonNull(livro, "Livro não pode ser nulo.");
        String sql = "INSERT INTO livro (titulo, exemplares_disponiveis) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getExemplaresDisponiveis());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerLivro(int livroId) {
        String sql = "DELETE FROM livro WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, livroId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerLivro(Livro livro) {
        Objects.requireNonNull(livro, "Livro não pode ser nulo.");
        removerLivro(livro.getId());
    }

    public Livro getLivro(int livroId) {
        String sql = "SELECT * FROM livro WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, livroId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("exemplares_disponiveis")
                );
            }

            throw new  NoSuchElementException("Livro " + livroId + " não existe");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        Objects.requireNonNull(usuario, "Usuario não pode ser nulo.");
        String sql = "INSERT INTO usuario (nome, bloqueado) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.isBloqueado() ? 1 : 0);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerUsuario(int usuarioId) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerUsuario(Usuario usuario) {
        Objects.requireNonNull(usuario, "Usuario não pode ser nulo.");
        removerUsuario(usuario.getId());
    }

    public Usuario getUsuario(int usuarioId) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("bloqueado") == 1
                );
            }

            throw new NoSuchElementException("Usuario " + usuarioId + " não existe");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void bloquearUsuario(int usuarioId) {
        String sql = "UPDATE usuario SET bloqueado = 1 WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void emprestar(int livroId, int usuarioId) {
        Livro livro = getLivro(livroId);
        livro.setExemplaresDisponiveis(livro.getExemplaresDisponiveis() - 1);

        String sqlRecuperaUsuario = """
                SELECT u.bloqueado AS bloqueado, COUNT(e.usuario_id) AS numero_emprestimos
                    FROM usuario u
                    LEFT JOIN emprestimo e ON u.id = e.usuario_id AND e.data_devolucao IS NULL
                    WHERE u.id = ?
                    GROUP BY u.id, u.bloqueado
                """;
        String sqlAtualizaLivro = "UPDATE livro SET exemplares_disponiveis = ? WHERE id = ?";
        String sqlFazEmprestimo = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_prevista) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt1 = connection.prepareStatement(sqlRecuperaUsuario)) {
            stmt1.setInt(1, usuarioId);

            ResultSet rsUsuario = stmt1.executeQuery();
            if (rsUsuario.next()) {
                boolean bloqueado = rsUsuario.getInt("bloqueado") == 1;
                int numeroEmprestimos = rsUsuario.getInt("numero_emprestimos");

                if (bloqueado || numeroEmprestimos >= 3) {
                    throw new IllegalStateException("Usuário " + usuarioId + " não pode fazer empréstimos");
                }
            } else {
                throw new NoSuchElementException("Usuário " + usuarioId + " não existe");
            }

            connection.setAutoCommit(false);
            try (PreparedStatement stmt2 = connection.prepareStatement(sqlAtualizaLivro);
                 PreparedStatement stmt3 = connection.prepareStatement(sqlFazEmprestimo)) {
                stmt2.setInt(1, livro.getExemplaresDisponiveis());
                stmt2.setInt(2, livro.getId());
                stmt2.executeUpdate();

                stmt3.setInt(1, livro.getId());
                stmt3.setInt(2, usuarioId);
                stmt3.setString(3, LocalDate.now().toString());
                stmt3.setString(4, LocalDate.now().plusDays(14).toString());
                stmt3.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Emprestimo getEmprestimo(int idEmprestimo) {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmprestimo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro livro = getLivro(rs.getInt("livro_id"));
                Usuario usuario = getUsuario(rs.getInt("usuario_id"));
                String dataDevolucao = rs.getObject("data_devolucao", String.class);
                return new Emprestimo(
                        rs.getInt("id"),
                        livro,
                        usuario,
                        LocalDate.parse(rs.getString("data_emprestimo")),
                        LocalDate.parse(rs.getString("data_prevista")),
                        dataDevolucao == null ? null : LocalDate.parse(dataDevolucao)
                );
            }

            throw new NoSuchElementException("Empréstimo " + idEmprestimo + " não existe");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double devolver(int idEmprestimo) {
        Emprestimo emprestimo = getEmprestimo(idEmprestimo);
        Livro livro = emprestimo.getLivro();
        Usuario usuario = emprestimo.getUsuario();

        String sqlIncrementar = "UPDATE livro SET exemplares_disponiveis = ? WHERE id = ?";
        String sqlDevolver = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ? AND data_devolucao IS NULL";
        String sqlBloquear = "UPDATE usuario SET bloqueado = 1 WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt1 = connection.prepareStatement(sqlIncrementar);
                 PreparedStatement stmt2 = connection.prepareStatement(sqlDevolver)) {
                livro.setExemplaresDisponiveis(livro.getExemplaresDisponiveis() + 1);
                stmt1.setInt(1, livro.getExemplaresDisponiveis());
                stmt1.setInt(2, livro.getId());
                stmt1.executeUpdate();

                emprestimo.setDataDevolucao(LocalDate.now());
                stmt2.setString(1, emprestimo.getDataDevolucao().toString());
                stmt2.setInt(2, emprestimo.getId());
                stmt2.executeUpdate();

                double multa = emprestimo.calcularMulta();
                if (multa != 0.0) {
                    try (PreparedStatement stmt3 = connection.prepareStatement(sqlBloquear)) {
                        usuario.setBloqueado(true);
                        stmt3.setInt(1, usuario.getId());
                        stmt3.executeUpdate();
                    }
                }

                connection.commit();
                return multa;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> atrasados() {
        String sql = """
                SELECT u.nome AS nome_usuario, l.titulo AS titulo_livro, e.data_prevista AS data_prevista
                        FROM usuario u
                        JOIN emprestimo e ON e.usuario_id = u.id
                        JOIN livro l ON e.livro_id = l.id
                        WHERE e.data_prevista < CURRENT_DATE AND e.data_devolucao IS NULL
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<String> lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(String.format(
                        "Nome do usuário: %s | Título do livro: %s | Dias de atraso: %s",
                        rs.getString("nome_usuario"),
                        rs.getString("titulo_livro"),
                        ChronoUnit.DAYS.between(LocalDate.parse(rs.getString("data_prevista")), LocalDate.now())
                ));
            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Livro> livrosMaisEmprestados() {
        String sql = """
                SELECT l.id AS livro_id, l.titulo AS livro_titulo, l.exemplares_disponiveis AS exemplares_disponiveis,
                        COUNT(e.livro_id) AS numero_emprestimos
                    FROM livro l JOIN emprestimo e ON e.livro_id = l.id
                    GROUP BY l.id, l.titulo, l.exemplares_disponiveis
                    ORDER BY COUNT(e.livro_id) DESC
                    LIMIT 5
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Livro> lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new Livro(
                        rs.getInt("livro_id"),
                        rs.getString("livro_titulo"),
                        rs.getInt("exemplares_disponiveis")
                ));
            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
