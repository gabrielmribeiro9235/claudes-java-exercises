package br.com.p3.exercises.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/school/school.db?foreign_keys=on";
    private DatabaseInitializer() {}

    public static void init() {
        try {
            System.out.println("> Criando as tabelas aluno, disciplina e matricula");
            createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createTables() throws SQLException {
        String sql = """
                DROP TABLE IF EXISTS matricula;
                DROP TABLE IF EXISTS aluno;
                DROP TABLE IF EXISTS disciplina;
                CREATE TABLE aluno (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL
                );
                CREATE TABLE disciplina (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    carga_horaria INTEGER NOT NULL
                );
                CREATE TABLE matricula (
                    aluno_id INTEGER NOT NULL REFERENCES aluno(id),
                    disciplina_id INTEGER NOT NULL REFERENCES disciplina(id),
                    nota1 REAL,
                    nota2 REAL,
                    PRIMARY KEY (aluno_id, disciplina_id)
                );
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
