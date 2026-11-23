package br.com.p3.exercises.gym;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SqlitePlanoRepository {
    private final static String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/gym/academia.db";

    public void adicionarPlano(Plano plano) {
        Objects.requireNonNull(plano, "Plano não pode ser nulo.");
        String sql = "INSERT INTO plano (nome, mensalidade) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getMensalidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPlano(String nome) {
        Objects.requireNonNull(nome, "Nome do plano não pode ser nulo.");
        String sql = "DELETE FROM plano WHERE nome = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPlano(Plano plano) {
        Objects.requireNonNull(plano, "Plano não pode ser nulo.");
        removerPlano(plano.getNome());
    }

    public Optional<Plano> getPlano(String nome) {
        Objects.requireNonNull(nome, "Nome do plano não pode ser nulo.");
        String sql = "SELECT * FROM plano WHERE nome = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Plano(
                        rs.getString("nome"),
                        rs.getDouble("mensalidade")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public List<Plano> getPlanos() {
        String sql = "SELECT * FROM plano";

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Plano> planos = new ArrayList<>();

            while (rs.next()) {
                planos.add(new Plano(
                        rs.getString("nome"),
                        rs.getDouble("mensalidade")
                ));
            }

            return planos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
