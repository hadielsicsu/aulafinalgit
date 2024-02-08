package com.hadiel.aulafinalgit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hadiel.aulafinalgit.model.Livro;
import com.hadiel.aulafinalgit.util.ConexaoDerby;

public class LivroDAO {

    // Método para recuperar todos os livros do banco de dados
    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "SELECT * FROM livros";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setGenero(rs.getString("genero"));
                livro.setNumeroPaginas(rs.getInt("numero_paginas"));
                livro.setResumo(rs.getString("resumo"));
                livro.setDisponivel(rs.getBoolean("disponivel"));

                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return livros;
    }

    // Método para adicionar um novo livro ao banco de dados
    public void adicionarLivro(Livro livro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "INSERT INTO livros (titulo, genero, numero_paginas, resumo, disponivel) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getGenero());
            stmt.setInt(3, livro.getNumeroPaginas());
            stmt.setString(4, livro.getResumo());
            stmt.setBoolean(5, livro.isDisponivel());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
    }
}
