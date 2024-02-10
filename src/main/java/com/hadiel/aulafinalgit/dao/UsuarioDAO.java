/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hadiel.aulafinalgit.dao;

import com.hadiel.aulafinalgit.model.Usuario;
import com.hadiel.aulafinalgit.util.ConexaoDerby;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() {

    }

    public boolean adicionarUsuario(Usuario usuario) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO usuario (nome, cpf, email, endereco, dataNascimento) VALUES (?, ?, ?, ?, ?)";
            conn = ConexaoDerby.obterConexao();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getEndereco());
            stmt.setString(5, usuario.getDataNascimento());
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

    public boolean editarUsuario(Usuario usuario) {
        PreparedStatement stmt = null;

        try {

            conn = ConexaoDerby.obterConexao();
            String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, endereco = ?, dataNascimento = ? WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getEndereco());
            stmt.setString(5, usuario.getDataNascimento());
            stmt.setInt(6, usuario.getId());
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

    public boolean excluirUsuario(int codigo) {
        PreparedStatement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();

            String sql = "DELETE FROM usuario WHERE codigo = ?";
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

    public List<Usuario> listarUsuarios(String clausula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario" + clausula;
        //System.out.println(sql);
        try {
            conn = ConexaoDerby.obterConexao();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("codigo"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setDataNascimento(rs.getString("dataNascimento"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario getUsuarioById(int id) {
        Usuario usuario = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM usuario WHERE codigo = ?";
        try {
            conn = ConexaoDerby.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("codigo"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setDataNascimento(rs.getString("dataNascimento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
