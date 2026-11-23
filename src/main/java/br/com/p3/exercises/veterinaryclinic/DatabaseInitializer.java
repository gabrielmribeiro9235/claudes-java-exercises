package br.com.p3.exercises.veterinaryclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/veterinaryclinic/clinic.db?foreign_keys=on";

    public static void init() {
        try {
            System.out.println("> Criando as tabelas tutor, pet e consulta");
            createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createTables() throws SQLException {
        String sql = """
                DROP TABLE IF EXISTS consulta;
                DROP TABLE IF EXISTS pet;
                DROP TABLE IF EXISTS tutor;
                CREATE TABLE tutor (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    telefone TEXT
                );
                CREATE TABLE pet (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    especie TEXT NOT NULL,
                    tutor_id INTEGER NOT NULL REFERENCES tutor(id)
                );
                CREATE TABLE consulta (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pet_id INTEGER NOT NULL REFERENCES pet(id),
                    data TEXT NOT NULL,
                    motivo TEXT NOT NULL,
                    valor REAL NOT NULL
                );
                """;
        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
