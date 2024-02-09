/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hadiel.aulafinalgit.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hadiel
 */
public class ExecutarScriptSQL {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = ConexaoDerby.obterConexao();

            String sqlScript = lerArquivoSQL("data/create_tables.sql");
            String[] comandos = sqlScript.split(";");
            for (String comando : comandos) {
                stmt = conn.createStatement();
                stmt.execute(comando.trim());
            }
            
            System.out.println("Script SQL executado com sucesso.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
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

    // Método para ler o conteúdo do arquivo SQL
    private static String lerArquivoSQL(String caminhoArquivo) throws IOException {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }
        return conteudo.toString();
    }
}