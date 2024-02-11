/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hadiel.aulafinalgit.model;


/**
 *
 * @author hadiel
 */
public class Emprestimo {
    private int codigo;
    private int livroCodigo;
    private int usuarioCodigo;
    private String status;
    private String dataLocacao;
    private String dataDevolucao;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the livroCodigo
     */
    public int getLivroCodigo() {
        return livroCodigo;
    }

    /**
     * @param livroCodigo the livroCodigo to set
     */
    public void setLivroCodigo(int livroCodigo) {
        this.livroCodigo = livroCodigo;
    }

    /**
     * @return the usuarioCodigo
     */
    public int getUsuarioCodigo() {
        return usuarioCodigo;
    }

    /**
     * @param usuarioCodigo the usuarioCodigo to set
     */
    public void setUsuarioCodigo(int usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the dataLocacao
     */
    public String getDataLocacao() {
        return dataLocacao;
    }

    /**
     * @param dataLocacao the dataLocacao to set
     */
    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    /**
     * @return the dataDevolucao
     */
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    
}
