package br.com.p3.exercises.productstock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/productstock/stock.db";

    private DatabaseInitializer() {}

    public static void init() {
        try {
            System.out.println("> Criando as tabelas categoria e produto");
            createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTables() throws SQLException {
        String sql = """
                DROP TABLE IF EXISTS produto;
                DROP TABLE IF EXISTS categoria;
                CREATE TABLE categoria (
                    id      INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome    TEXT NOT NULL UNIQUE
                );
                CREATE TABLE produto (
                    id              INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome            TEXT NOT NULL,
                    preco           REAL NOT NULL CHECK (preco >= 0),
                    quantidade      INTEGER NOT NULL CHECK (quantidade >= 0),
                    categoria_id    INTEGER NOT NULL REFERENCES categoria(id)
                );
                """;
        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
