package br.com.p3.exercises.school;

import java.sql.*;
import java.util.*;

public class SchoolManager {
    private static final String URL = "jdbc:sqlite:src/main/java/br/com/p3/exercises/school/school.db?foreign_keys=on";

    public void adicionarAluno(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        String sql = "INSERT INTO aluno (nome) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());

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

    public void adicionarDisciplina(Disciplina disciplina) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nulo.");
        String sql = "INSERT INTO disciplina (nome, carga_horaria) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerDisciplina(int id) {
        String sql = "DELETE FROM disciplina WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nulo.");
        removerDisciplina(disciplina.getId());
    }

    public void adicionarMatricula(Aluno aluno, Disciplina disciplina) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula.");
        String sql = "INSERT INTO matricula (aluno_id, disciplina_id, nota1, nota2) VALUES (?, ?, null, null)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, disciplina.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarMatricula(Matricula matricula) {
        Objects.requireNonNull(matricula, "Matricula não pode ser nulo.");
        String sql = "INSERT INTO matricula (aluno_id, disciplina_id, nota1, nota2) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, matricula.getAluno().getId());
            stmt.setInt(2, matricula.getDisciplina().getId());
            stmt.setDouble(3, matricula.getNota1());
            stmt.setDouble(4, matricula.getNota2());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerMatricula(int alunoId, int disciplinaId) {
        String sql = "DELETE FROM matricula WHERE aluno_id = ? AND disciplina_id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);
            stmt.setInt(2, disciplinaId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerMatricula(Matricula matricula) {
        Objects.requireNonNull(matricula, "Matricula não pode ser nulo.");
        removerMatricula(matricula.getAluno().getId(), matricula.getDisciplina().getId());
    }

    public void lancarNotas(Matricula matricula) {
        Objects.requireNonNull(matricula, "Matricula não pode ser nula.");
        String sql = "UPDATE matricula SET nota1 = ?, nota2 = ? WHERE aluno_id = ? AND disciplina_id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, matricula.getNota1());
            stmt.setDouble(2, matricula.getNota2());
            stmt.setInt(3, matricula.getAluno().getId());
            stmt.setInt(4, matricula.getDisciplina().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno getAluno(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Disciplina getDisciplina(int id) {
        String sql = "SELECT * FROM disciplina WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Disciplina(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("carga_horaria")
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Matricula getMatricula(int alunoId, int disciplinaId) {
        String sql = "SELECT * FROM matricula WHERE aluno_id = ? AND disciplina_id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);
            stmt.setInt(2, disciplinaId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = getAluno(alunoId);
                Disciplina disciplina = getDisciplina(disciplinaId);

                return new Matricula(
                        aluno,
                        disciplina,
                        rs.getDouble("nota1"),
                        rs.getDouble("nota2")
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Matricula> getMatriculas() {
        String sql = "SELECT * FROM matricula";

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Matricula> matriculas = new ArrayList<>();

            while (rs.next()) {
                Aluno aluno = getAluno(rs.getInt("aluno_id"));
                Disciplina disciplina = getDisciplina(rs.getInt("disciplina_id"));

                matriculas.add(new Matricula(
                        aluno,
                        disciplina,
                        rs.getDouble("nota1"),
                        rs.getDouble("nota2")
                ));
            }

            return matriculas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Matricula> boletim(int alunoId) {
        String sql = "SELECT * FROM matricula WHERE aluno_id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);

            ResultSet rs = stmt.executeQuery();

            List<Matricula> matriculas = new ArrayList<>();

            while (rs.next()) {
                Aluno aluno = getAluno(alunoId);
                Disciplina disciplina = getDisciplina(rs.getInt("disciplina_id"));

                matriculas.add(new Matricula(
                        aluno,
                        disciplina,
                        (Double) rs.getObject("nota1"),
                        (Double) rs.getObject("nota2")
                ));
            }

            return matriculas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Aluno> rankingAlunos() {
        String sql = """
                SELECT a.id AS id_aluno, a.nome AS nome_aluno,
                        AVG((m.nota1 + m.nota2) / 2.0) AS media_geral
                    FROM aluno a
                    JOIN matricula m ON a.id = m.aluno_id
                GROUP BY a.id, a.nome
                HAVING COUNT(*) = SUM(
                    CASE
                        WHEN m.nota1 IS NOT NULL AND m.nota2 IS NOT NULL THEN 1
                        ELSE 0
                    END
                )
                ORDER BY media_geral DESC
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Aluno> ranking = new ArrayList<>();

            while (rs.next()) {
                ranking.add(new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getString("nome_aluno")
                ));
            }

            return ranking;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Aluno> alunosReprovadosMaisDeUmaVez() {
        Set<Aluno> jaReprovados = new HashSet<>();
        Set<Aluno> reprovadosEmMaisDeUma = new LinkedHashSet<>();

        for (Matricula m : getMatriculas()) {
            if ("reprovado".equals(m.getSituacao())) {
                Aluno aluno = m.getAluno();

                if (!jaReprovados.add(aluno)) {
                    reprovadosEmMaisDeUma.add(aluno);
                }
            }
        }

        return new ArrayList<>(reprovadosEmMaisDeUma);
    }
}
