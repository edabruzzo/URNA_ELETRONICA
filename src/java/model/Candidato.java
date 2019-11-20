/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ConexaoDAO;
import dao.EleicaoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emm
 */
public class Candidato {
    
    private int idCandidato;
    private int numeroCandidato;
    private String nomeCandidato;
    private Partido partido;
    private int idEleicao;

    
    
    
    public String eleicaoCandidato(){
        
        EleicaoDAO eleicaoDAO = new EleicaoDAO();
        Connection conn = new ConexaoDAO().criaConexao();
        Eleicao eleicao = new Eleicao();
        try {
        
            eleicao = eleicaoDAO.buscaById(conn, this.getIdEleicao());
        
        } catch (SQLException ex) {
            Logger.getLogger(Candidato.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return eleicao.getCargo();
        
        
    }
    
    

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getNumeroCandidato() {
        return numeroCandidato;
    }

    public void setNumeroCandidato(int numeroCandidato) {
        this.numeroCandidato = numeroCandidato;
    }


    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public int getIdEleicao() {
        return idEleicao;
    }

    public void setIdEleicao(int idEleicao) {
        this.idEleicao = idEleicao;
    }


    
    
    
    
}
