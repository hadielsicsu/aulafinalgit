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

    public LivroDAO() {

    }

    // Método para recuperar todos os livros do banco de dados
    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "SELECT * FROM livro";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("codigo"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setGenero(rs.getString("genero"));
                livro.setNumeroPaginas(rs.getInt("paginas"));
                livro.setResumo(rs.getString("sinopse"));
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
            String sql = "INSERT INTO livro (titulo, genero, paginas, sinopse, disponivel) VALUES (?, ?, ?, ?, ?)";
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

    public void editarLivro(Livro livro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "UPDATE livro SET titulo = ?, genero = ?, paginas = ?, sinopse = ?, disponivel = ? WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getGenero());
            stmt.setInt(3, livro.getNumeroPaginas());
            stmt.setString(4, livro.getResumo());
            stmt.setBoolean(5, livro.isDisponivel());
            stmt.setInt(6, livro.getId());

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

    public void excluirLivro(int codigoLivro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "DELETE FROM livro WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigoLivro);

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
