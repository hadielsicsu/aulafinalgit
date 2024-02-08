package com.hadiel.aulafinalgit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDerby {
    // Método para obter uma conexão com o banco de dados
    public static Connection obterConexao() throws SQLException {
        String url = "jdbc:derby:biblioteca;create=true"; 

        String usuario = "usuario";
        String senha = "SenhaBiblioteca";

        Connection conexao = DriverManager.getConnection(url, usuario, senha);

        return conexao;
    }
}
