package org.example.dao;

import org.example.conexao.Conexao;
import org.example.model.Emprestimo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {

    public static boolean inserirEmprestimo(Emprestimo emprestimo) {
        String sqlEmprestimo = "INSERT INTO emprestimo (id_usuario, id_epi, data_retirada, data_prevista_devolucao, confirmacao_retirada) VALUES (?, ?, ?, ?, ?)";
        String sqlAtualizarEstoque = "UPDATE epi SET quantidade = quantidade - 1 WHERE id_epi = ? AND quantidade > 0";

        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false); // inicia transação

            // Inserir o empréstimo
            try (PreparedStatement stmt = conn.prepareStatement(sqlEmprestimo)) {
                stmt.setInt(1, emprestimo.getIdUsuario());
                stmt.setInt(2, emprestimo.getIdEpi());
                stmt.setTimestamp(3, java.sql.Timestamp.valueOf(emprestimo.getDataRetirada()));
                stmt.setTimestamp(4, java.sql.Timestamp.valueOf(emprestimo.getDataPrevistaDevolucao()));
                stmt.setBoolean(5, emprestimo.isConfirmacaoRetirada());
                stmt.executeUpdate();
            }

            // Atualizar a quantidade do EPI
            try (PreparedStatement stmtEstoque = conn.prepareStatement(sqlAtualizarEstoque)) {
                stmtEstoque.setInt(1, emprestimo.getIdEpi());
                int linhasAfetadas = stmtEstoque.executeUpdate();

                if (linhasAfetadas == 0) {
                    conn.rollback();
                    System.out.println("Estoque insuficiente para este EPI.");
                    return false;
                }
            }

            conn.commit();
            System.out.println("Empréstimo registrado com sucesso!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir empréstimo.");
            return false;
        }
    }


    public ArrayList<Emprestimo> listarEmprestimos() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Emprestimo emp = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_epi"),
                        rs.getTimestamp("data_retirada").toLocalDateTime(),
                        rs.getTimestamp("data_prevista_devolucao").toLocalDateTime(),
                        rs.getBoolean("confirmacao_retirada")
                );
                lista.add(emp);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public boolean atualizarEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET id_usuario = ?, id_epi = ?, data_retirada = ?, data_prevista_devolucao = ?, confirmacao_retirada = ? WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getIdUsuario());
            stmt.setInt(2, emprestimo.getIdEpi());
            stmt.setTimestamp(3, Timestamp.valueOf(emprestimo.getDataRetirada()));
            stmt.setTimestamp(4, Timestamp.valueOf(emprestimo.getDataPrevistaDevolucao()));
            stmt.setBoolean(5, emprestimo.isConfirmacaoRetirada());
            stmt.setInt(6, emprestimo.getIdEmprestimo());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empréstimo: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirEmprestimo(int id) {
        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir empréstimo: " + e.getMessage());
            return false;
        }
    }

    public static List<Emprestimo> listarEmprestimosPorUsuario(int idUsuario) {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo WHERE id_usuario = ? AND id_emprestimo NOT IN (SELECT id_emprestimo FROM devolucao)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Emprestimo e = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_epi"),
                        rs.getTimestamp("data_retirada").toLocalDateTime(),
                        rs.getTimestamp("data_prevista_devolucao").toLocalDateTime(),
                        rs.getBoolean("confirmacao_retirada")
                );
                lista.add(e);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public boolean epiEmUso(int idEpi) {
        String sql = "SELECT COUNT(*) AS total FROM emprestimo WHERE id_epi = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEpi);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0; // true se houver empréstimos com esse EPI
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
