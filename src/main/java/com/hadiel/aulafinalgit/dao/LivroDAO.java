package com.hadiel.aulafinalgit.dao;

import com.hadiel.aulafinalgit.model.Autor;
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
    public List<Livro> listarLivros(String clausula) {
        List<Livro> livros = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "SELECT * FROM livro" + clausula;
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
            System.out.println(livro.getGenero());
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

    public List<Autor> getAutoresVinculados(int codigoLivro) {
        List<Autor> autores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "SELECT autor.* FROM autor INNER JOIN livro_autor ON autor.codigo = livro_autor.autor_codigo WHERE livro_autor.livro_codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigoLivro);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Autor autor = new Autor();
                    autor.setId(rs.getInt("codigo"));
                    autor.setNome(rs.getString("nome"));
                    autor.setNacionalidade(rs.getString("nacionalidade"));
                    autores.add(autor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    public void vinculaAutor(int codigoLivro, int codigoAutor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "INSERT INTO livro_autor (livro_codigo, autor_codigo) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigoLivro);
            stmt.setInt(2, codigoAutor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void desvinculaAutor(int codigoLivro, int codigoAutor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "DELETE FROM livro_autor WHERE livro_codigo = ? AND autor_codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigoLivro);
            stmt.setInt(2, codigoAutor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean excluirLivro(int codigoLivro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "DELETE FROM livro WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigoLivro);

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

    public Livro getLivroById(int id) {
        Livro livro = null;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();
            String sql = "SELECT * FROM livro WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    livro = new Livro();
                    livro.setId(rs.getInt("codigo"));
                    livro.setTitulo(rs.getString("titulo"));
                    livro.setGenero(rs.getString("genero"));
                    livro.setNumeroPaginas(rs.getInt("paginas"));
                    livro.setResumo(rs.getString("sinopse"));
                    livro.setDisponivel(rs.getBoolean("disponivel"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }
}
