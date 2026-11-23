package br.com.p3.exercises.gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private final static String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/gym/academia.db";

    private DatabaseInitializer() {}

    public static void init() {
        try {
            System.out.println("> Criando as Tabelas plano e aluno");
            createTables();
        } catch (SQLException e) {
            System.err.println("Erro ao tentar criar as tabelas");
        }
    }

    private static void createTables() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS plano (
                    id             INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome           TEXT NOT NULL,
                    mensalidade    REAL NOT NULL
                );
                CREATE TABLE IF NOT EXISTS aluno (
                    id              INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome            TEXT NOT NULL,
                    data_nascimento TEXT NOT NULL,
                    ativo           INTEGER NOT NULL DEFAULT 1,
                    plano_id        INTEGER NOT NULL REFERENCES plano(id)
                );
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
