package br.com.p3.exercises.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/library/library.db?foreign_keys=on";

    private DatabaseInitializer() {}

    public static void init() {
        try {
            System.out.println("> Criando as tabelas livro, usuario e emprestimo");
            createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createTables() throws SQLException {
        String sql = """
                DROP TABLE IF EXISTS emprestimo;
                DROP TABLE IF EXISTS livro;
                DROP TABLE IF EXISTS usuario;
                CREATE TABLE livro (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    exemplares_disponiveis INTEGER NOT NULL CHECK (exemplares_disponiveis >= 0)
                );
                CREATE TABLE usuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    bloqueado INTEGER NOT NULL DEFAULT 0
                );
                CREATE TABLE emprestimo (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    livro_id INTEGER NOT NULL REFERENCES livro(id),
                    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
                    data_emprestimo TEXT NOT NULL,
                    data_prevista TEXT NOT NULL,
                    data_devolucao TEXT
                );
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();) {
            stmt.executeUpdate(sql);
        }
    }
}
