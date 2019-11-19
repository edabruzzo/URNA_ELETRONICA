/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Emm
 */
public class Eleicao {
    
    private int id_eleicao;
    private String cargo;
    private String unidadeFederacao;
    
    //referencia na tb_voto
    private List<Candidato> listaCandidatos;
    private List<Voto> votos;

    public int getId_eleicao() {
        return id_eleicao;
    }

    public void setId_eleicao(int id_eleicao) {
        this.id_eleicao = id_eleicao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(String unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public List<Candidato> getListaCandidatos() {
        return listaCandidatos;
    }

    public void setListaCandidatos(List<Candidato> listaCandidatos) {
        this.listaCandidatos = listaCandidatos;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
    
    
}
