/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Candidato;
import model.Eleitor;
import model.Partido;

/**
 *
 * @author Emm
 */
public class CandidatoDAO extends ConexaoDAO {

    PartidoDAO partidoDAO = new PartidoDAO();
    EleitorDAO eleitorDAO = new EleitorDAO();

    public boolean incluirCandidato(Candidato candidato) throws SQLException {

        boolean sucesso = false;

        Connection conn = this.criaConexao();
        String sql = "INSERT INTO tb_candidato( \n"
                + "	id_partido,\n"
                + "	nome_candidato,\n"
                + "	numero_candidato)values\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "'?');\n";

        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);

            stm.setInt(1, candidato.getPartido().getId_partido());
            stm.setString(2, candidato.getNomeCandidato());
            stm.setInt(3, candidato.getNumeroCandidato());
            stm.executeUpdate();
            sucesso = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            //ex.printStackTrace();        
            //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            stm.close();
            this.fecharConexao(conn);

        }

        return sucesso;
    }

    public boolean removerCandidato(int idCandidato) throws SQLException {

        boolean sucesso = false;

        Connection conn = this.criaConexao();
        String sql = "DELETE FROM tb_candidato WHERE id_candidato = ?";

        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);

            stm.setInt(1, idCandidato);
            stm.executeUpdate();
            sucesso = true;

        } catch (SQLException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            stm.close();
            this.fecharConexao(conn);

        }

        return sucesso;
    }

    public boolean atualizarCandidato(Candidato candidato) throws SQLException {

        boolean sucesso = false;

        Connection conn = this.criaConexao();
        String sql = "UPDATE tb_candidato SET \n"
                + "numero_candidato = ?, \n"
                + "nome_candidato = ?, \n"
                + "id_partido = ?, \n"
                + "WHERE id_candidato = ?;";

        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);

            stm.setInt(4, candidato.getPartido().getId_partido());
            stm.setInt(1, candidato.getNumeroCandidato());
            stm.setString(2, candidato.getNomeCandidato());
            stm.setInt(5, candidato.getIdCandidato());
            stm.executeUpdate();
            sucesso = true;

        } catch (SQLException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return sucesso;

        } finally {

            stm.close();
            this.fecharConexao(conn);

        }

        return sucesso;
    }

    public List<Candidato> listarCandidatos(String sql) throws SQLException {

        Connection conn = this.criaConexao();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        List<Candidato> listaCandidatos = new ArrayList<Candidato>();

        while (rs.next()) {

            Candidato candidato = new Candidato();
            int idCanditado = rs.getInt("id_candidato");
            int numeroCandidato = rs.getInt("numero_candidato");
            Partido partido = partidoDAO.buscaById(rs.getInt("id_partido"));
            String nomeCandidato = rs.getString("nome_candidato");

            candidato.setIdCandidato(idCanditado);
            candidato.setNumeroCandidato(numeroCandidato);
            candidato.setNomeCandidato(nomeCandidato);
            candidato.setPartido(partido);

            listaCandidatos.add(candidato);
        }

        stm.close();
        rs.close();
        this.fecharConexao(conn);

        return listaCandidatos;

    }

    public List<Candidato> listarTodosCandidatos() throws SQLException {

        Connection conn = this.criaConexao();
        Statement stm = conn.createStatement();
        String sql = "SELECT * FROM tb_candidato";
        ResultSet rs = stm.executeQuery(sql);

        List<Candidato> listaCandidatos = new ArrayList<Candidato>();

        while (rs.next()) {

            Candidato candidato = new Candidato();
            int idCanditado = rs.getInt("id_candidato");
            int numeroCandidato = rs.getInt("numero_candidato");
            Partido partido = partidoDAO.buscaById(rs.getInt("id_partido"));
            String nomeCandidato = rs.getString("nome_candidato");

            candidato.setIdCandidato(idCanditado);
            candidato.setNumeroCandidato(numeroCandidato);
            candidato.setNomeCandidato(nomeCandidato);
            candidato.setPartido(partido);

            listaCandidatos.add(candidato);
        }

        stm.close();
        rs.close();
        this.fecharConexao(conn);

        return listaCandidatos;

    }

    public Candidato buscarCandidato(int idCandidato) throws SQLException {

        Connection conn = this.criaConexao();
        String sql = "SELECT * FROM tb_candidato WHERE id_candidato = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, idCandidato);

        ResultSet rs = stm.executeQuery(sql);
        Candidato candidato = new Candidato();

        while (rs.next()) {

            int idCanditado = rs.getInt("id_candidato");
            int numeroCandidato = rs.getInt("numero_candidato");
            String nomeCandidato = rs.getString("nome_candidato");
            Partido partido = partidoDAO.buscaById(rs.getInt("id_partido"));
            
            candidato.setIdCandidato(idCanditado);
            candidato.setNumeroCandidato(numeroCandidato);
            candidato.setNomeCandidato(nomeCandidato);
            candidato.setPartido(partido);

        }

        stm.close();
        rs.close();
        this.fecharConexao(conn);

        return candidato;

    }

    Candidato buscarCandidatoByNumeroCandidato(int numeroCandidato) throws SQLException {
    
        Connection conn = this.criaConexao();
        String sql = "SELECT * FROM tb_candidato WHERE numero_candidato = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, numeroCandidato);

        ResultSet rs = stm.executeQuery(sql);
        Candidato candidato = new Candidato();

        while (rs.next()) {

            int idCanditado = rs.getInt("id_candidato");
            String nomeCandidato = rs.getString("nome_candidato");
            Partido partido = partidoDAO.buscaById(rs.getInt("id_partido"));
            
            candidato.setIdCandidato(idCanditado);
            candidato.setNumeroCandidato(rs.getInt("numero_candidato"));
            candidato.setNomeCandidato(nomeCandidato);
            candidato.setPartido(partido);

        }

        stm.close();
        rs.close();
        this.fecharConexao(conn);

        return candidato;

    
    
    }

}
