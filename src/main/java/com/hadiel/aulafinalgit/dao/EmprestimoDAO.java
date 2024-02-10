/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hadiel.aulafinalgit.dao;

import com.hadiel.aulafinalgit.model.Emprestimo;
import com.hadiel.aulafinalgit.util.ConexaoDerby;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    private Connection conn;

    public EmprestimoDAO() {

    }

    public boolean realizarEmprestimo(Emprestimo emprestimo) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO item (livro_codigo, usuario_codigo, status, dataLocacao, dataDevolucao) VALUES (?, ?, ?, ?, ?)";
            conn = ConexaoDerby.obterConexao();

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, emprestimo.getLivroCodigo());
            stmt.setInt(2, emprestimo.getUsuarioCodigo());
            stmt.setString(3, emprestimo.getStatus());
            stmt.setString(4, emprestimo.getDataLocacao());
            stmt.setString(5, emprestimo.getDataDevolucao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    public boolean atualizarEmprestimo(Emprestimo emprestimo) {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE item SET status = ?, dataLocacao = ?, dataDevolucao = ? WHERE codigo = ?";
            conn = ConexaoDerby.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, emprestimo.getStatus());
            stmt.setString(2, emprestimo.getDataLocacao());
            stmt.setString(3, emprestimo.getDataDevolucao());
            stmt.setInt(4, emprestimo.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public boolean devolverLivro(int codigoEmprestimo) {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE item SET status = 'disponivel' WHERE codigo = ?";
            conn = ConexaoDerby.obterConexao();

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigoEmprestimo);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public List<Emprestimo> listarEmprestimos(String clausula) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM item"+clausula;

            conn = ConexaoDerby.obterConexao();

            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setCodigo(rs.getInt("codigo"));
                emprestimo.setLivroCodigo(rs.getInt("livro_codigo"));
                emprestimo.setUsuarioCodigo(rs.getInt("usuario_codigo"));
                emprestimo.setStatus(rs.getString("status"));
                emprestimo.setDataLocacao(rs.getString("dataLocacao"));
                emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
    public boolean verificarPendencias(int idUsuario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Obter a data atual
            LocalDate hoje = LocalDate.now();

            // Formatar a data atual para comparar com a data de devolução
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataAtual = hoje.format(formato);

            String sql = "SELECT dataDevolucao FROM item WHERE usuario_codigo = ? AND status = 'Emprestado'";
            conn = ConexaoDerby.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String dataDevolucao = rs.getString("dataDevolucao");
                if (dataDevolucao != null && isDataAtrasada(dataDevolucao, dataAtual)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private boolean isDataAtrasada(String dataDevolucao, String dataAtual) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate devolucao = LocalDate.parse(dataDevolucao, formato);
        LocalDate atual = LocalDate.parse(dataAtual, formato);
        return devolucao.isBefore(atual);
    }

}
