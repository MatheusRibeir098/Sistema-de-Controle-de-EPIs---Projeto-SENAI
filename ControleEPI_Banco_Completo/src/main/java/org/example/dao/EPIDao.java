package org.example.dao;

import org.example.conexao.Conexao;
import org.example.model.EPI;

import java.sql.*;
import java.util.ArrayList;

public class EPIDao {

    // Método para inserir um novo EPI
    public boolean inserirEPI(EPI epi) {
        String sql = "INSERT INTO epi (nome, quantidade) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, epi.getNome());
            stmt.setInt(2, epi.getQuantidade());  // Usando a quantidade no insert
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir EPI: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os EPIs
    public ArrayList<EPI> listarEPIs() {
        ArrayList<EPI> lista = new ArrayList<>();
        String sql = "SELECT * FROM epi";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EPI epi = new EPI(
                        rs.getInt("id_epi"),               // ID do EPI
                        rs.getString("nome"),          // Nome do EPI
                        rs.getInt("quantidade")        // Quantidade do EPI
                );
                lista.add(epi);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar EPIs: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Método para atualizar as informações de um EPI
    public boolean atualizarEPI(int id, String nome, int quantidade) {
        String sql = "UPDATE epi SET nome = ?, quantidade = ? WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, quantidade);  // Atualizando a quantidade
            stmt.setInt(3, id);  // Atualizando pelo id
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar EPI: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para excluir um EPI
    public boolean excluirEPI(int id) {
        String sql = "DELETE FROM epi WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Exclusão pelo id
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir EPI: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


}
