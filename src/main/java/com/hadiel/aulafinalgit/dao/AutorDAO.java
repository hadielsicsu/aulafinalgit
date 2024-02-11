/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hadiel.aulafinalgit.dao;

import com.hadiel.aulafinalgit.model.Autor;
import com.hadiel.aulafinalgit.util.ConexaoDerby;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private Connection conn;

    public AutorDAO() {

    }

    public boolean adicionarAutor(Autor autor) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO autor (nome, nacionalidade) VALUES (?, ?)";
            conn = ConexaoDerby.obterConexao();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
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

    public boolean editarAutor(Autor autor) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "UPDATE autor SET nome = ?, nacionalidade = ? WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
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

    public boolean excluirAutor(int codigo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "DELETE FROM autor WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
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

    public List<Autor> listarAutores(String clausula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor"+clausula;
        try {
            conn = ConexaoDerby.obterConexao();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("codigo"));
                autor.setNome(rs.getString("nome"));
                autor.setNacionalidade(rs.getString("nacionalidade"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}
