package br.com.p3.exercises.gym;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class SqliteAlunoRepository {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/gym/academia.db";
    private final SqlitePlanoRepository planoRepository = new SqlitePlanoRepository();

    public void adicionarAluno(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        String sql = "INSERT INTO aluno (nome, data_nascimento, ativo, plano_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getDataNascimento().toString());
            stmt.setInt(3, aluno.isAtivo() ? 1 : 0);
            stmt.setInt(4, aluno.getPlano().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerAluno(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        removerAluno(aluno.getId());
    }

    public void atualizarAluno(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        String sql = "UPDATE aluno SET ativo = ?, plano_id = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, aluno.isAtivo() ? 1 : 0);
            stmt.setInt(2, aluno.getPlano().getId());
            stmt.setInt(3, aluno.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Aluno> getAluno(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Plano plano = planoRepository.getPlano(rs.getInt("plano_id"))
                        .orElseThrow(() -> new NoSuchElementException("O plano do aluno " + id + " não existe"));

                return Optional.of(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        LocalDate.parse(rs.getString("data_nascimento")),
                        rs.getInt("ativo") == 1,
                        plano
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public List<Aluno> getAlunos() {
        String sql = "SELECT * FROM aluno";

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Aluno> alunos = new ArrayList<>();

            while (rs.next()) {
                Plano plano = planoRepository.getPlano(rs.getInt("plano_id"))
                        .orElseThrow(() -> new NoSuchElementException("O plano do aluno não foi encontrado"));

                alunos.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        LocalDate.parse(rs.getString("data_nascimento")),
                        rs.getInt("ativo") == 1,
                        plano
                ));
            }

            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Aluno> getAlunosPorPlano(Plano plano) {
        Objects.requireNonNull(plano, "Plano não pode ser nulo.");
        String sql = """
                SELECT a.id, a.nome, a.data_nascimento, a.ativo, p.id
                    FROM aluno a JOIN plano p ON p.id = a.plano_id AND p.id = ?
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,  plano.getId());
            ResultSet rs = stmt.executeQuery();
            List<Aluno> alunos = new ArrayList<>();

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        LocalDate.parse(rs.getString("data_nascimento")),
                        rs.getInt("ativo") == 1,
                        plano
                ));
            }

            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
