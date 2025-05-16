package org.example.dao;

import org.example.conexao.Conexao;
import org.example.model.Devolucao;
import org.example.model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DevolucaoDao {
    public static boolean registrarDevolucao(int idEmprestimo, LocalDateTime dataDevolucao) {
        String sqlDevolucao = "INSERT INTO devolucao (id_emprestimo, data_devolucao) VALUES (?, ?)";
        String sqlBuscarEpi = "SELECT id_epi FROM emprestimo WHERE id_emprestimo = ?";
        String sqlAtualizarEstoque = "UPDATE epi SET quantidade = quantidade + 1 WHERE id_epi = ?";

        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false);

            // Inserir devolução
            try (PreparedStatement stmt = conn.prepareStatement(sqlDevolucao)) {
                stmt.setInt(1, idEmprestimo);
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(dataDevolucao));
                stmt.executeUpdate();
            }

            // Buscar o id_epi do empréstimo
            int idEpi = -1;
            try (PreparedStatement stmtBusca = conn.prepareStatement(sqlBuscarEpi)) {
                stmtBusca.setInt(1, idEmprestimo);
                ResultSet rs = stmtBusca.executeQuery();
                if (rs.next()) {
                    idEpi = rs.getInt("id_epi");
                } else {
                    conn.rollback();
                    System.out.println("Empréstimo não encontrado.");
                    return false;
                }
            }

            // Atualizar estoque
            try (PreparedStatement stmtEstoque = conn.prepareStatement(sqlAtualizarEstoque)) {
                stmtEstoque.setInt(1, idEpi);
                stmtEstoque.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar devolução.");
            return false;
        }
    }

    public static List<Devolucao> listarDevolucaoPorUsuario(int idUsuario) {
        List<Devolucao> lista = new ArrayList<>();
        String sql = "SELECT d.* FROM devolucao d " +
                "JOIN emprestimo e ON d.id_emprestimo = e.id_emprestimo " +
                "WHERE e.id_usuario = ?;";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Devolucao d = new Devolucao(
                        rs.getInt("id_devolucao"),
                        rs.getInt("id_emprestimo"),
                        rs.getTimestamp("data_devolucao")
                );
                lista.add(d);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar devoluções: " + e.getMessage());
        }
        return lista;
    }


}
