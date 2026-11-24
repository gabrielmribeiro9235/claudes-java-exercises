package br.com.p3.exercises.veterinaryclinic;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VeterinaryClinic {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/veterinaryclinic/clinic.db?foreign_keys=on";

    public void adicionarTutor(Tutor tutor) {
        Objects.requireNonNull(tutor, "Tutor não pode ser nulo.");
        String sqlTutor = "INSERT INTO tutor (nome, telefone) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt1 = connection.prepareStatement(sqlTutor)) {

                stmt1.setString(1, tutor.getNome());
                stmt1.setString(2, tutor.getTelefone());

                stmt1.executeUpdate();

                for (Pet pet : tutor.getPets()) {
                    String sqlPet = "INSERT INTO pet (nome, especie, tutor_id) VALUES (?, ?, ?)";
                    PreparedStatement stmt2 = connection.prepareStatement(sqlPet);

                    stmt2.setString(1, pet.getNome());
                    stmt2.setString(2, pet.especie());
                    stmt2.setInt(3, tutor.getId());

                    stmt2.executeUpdate();
                }

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

    public Tutor getTutor(int id) {
        String sql = """
                SELECT t.id AS tutor_id, t.nome AS tutor_nome, t.telefone AS tutor_fone,
                        p.id AS pet_id, p.nome AS pet_nome, p.especie AS pet_especie
                    FROM tutor t
                    LEFT JOIN pet p ON t.id = p.tutor_id
                    WHERE t.id = ?;
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Tutor tutor = new Tutor(
                        rs.getInt("tutor_id"),
                        rs.getString("tutor_nome"),
                        rs.getString("tutor_fone")
                );

                String especie = rs.getString("pet_especie");
                switch (especie) {
                    case "CACHORRO" -> tutor.adicionarPet(new Cachorro(rs.getInt("pet_id"), rs.getString("pet_nome"), tutor));
                    case "GATO" -> tutor.adicionarPet(new Gato(rs.getInt("pet_id"), rs.getString("pet_nome"), tutor));
                    default -> tutor.adicionarPet(new Outras(rs.getInt("pet_id"), rs.getString("pet_nome"), tutor));
                }

                while (rs.next()) {
                    especie = rs.getString("pet_especie");
                    switch (especie) {
                        case "CACHORRO" -> tutor.adicionarPet(new Cachorro(rs.getInt("pet_id"), rs.getString("pet_nome"), tutor));
                        case "GATO" -> tutor.adicionarPet(new Gato(rs.getInt("pet_id"), rs.getString("pet_nome"), tutor));
                        default -> tutor.adicionarPet(new Outras(rs.getInt("pet_id"), rs.getString("pet_nome"), tutor));
                    }
                }

                return tutor;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarConsulta(Consulta consulta) {
        String sql = "INSERT INTO consulta (pet_id, data, motivo, valor) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getPet().getId());
            stmt.setString(2, consulta.getData().toString());
            stmt.setString(3, consulta.getMotivo());
            stmt.setDouble(4, consulta.getValor());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getConsultas(Pet pet) {
        Objects.requireNonNull(pet, "Pet não pode ser nulo.");
        String sql = "SELECT id, data, motivo, valor FROM consulta WHERE pet_id = ? ORDER BY data";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pet.getId());

            ResultSet rs = stmt.executeQuery();

            List<Consulta> consultas = new ArrayList<>();
            while (rs.next()) {
                consultas.add(new Consulta(
                        rs.getInt("id"),
                        pet,
                        LocalDate.parse(rs.getString("data")),
                        rs.getString("motivo"),
                        rs.getDouble("valor")
                ));
            }

            return consultas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tutor> getTresTutoresComMaisGastos() {
        String sql = """
                SELECT t.id AS tutor_id, t.nome AS tutor_nome, t.telefone AS tutor_fone, SUM(c.valor) AS gastos_consulta
                    FROM tutor t JOIN pet p ON t.id = p.tutor_id
                        JOIN consulta c ON p.id = c.pet_id
                GROUP BY tutor_id
                ORDER BY gastos_consulta DESC
                LIMIT 3
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Tutor> tutores = new ArrayList<>();

            while (rs.next()) {
                Tutor tutor = new Tutor(
                        rs.getInt("tutor_id"),
                        rs.getString("tutor_nome"),
                        rs.getString("tutor_fone")
                );

                tutores.add(tutor);
            }

            return tutores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPet(Pet pet) {
        Objects.requireNonNull(pet, "Pet não pode ser nulo.");
        String sqlConsulta = "DELETE FROM consulta WHERE pet_id = ?";
        String sqlPet = "DELETE FROM pet WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            try (PreparedStatement stmtConsulta = connection.prepareStatement(sqlConsulta);
                 PreparedStatement stmtPet = connection.prepareStatement(sqlPet)) {
                stmtConsulta.setInt(1, pet.getId());
                stmtConsulta.executeUpdate();

                stmtPet.setInt(1, pet.getId());
                stmtPet.executeUpdate();

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
}
