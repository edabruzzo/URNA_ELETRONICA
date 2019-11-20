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
import model.Eleicao;
import model.Partido;

/**
 *
 * @author Emm
 */
public class CandidatoDAO {

    PartidoDAO partidoDAO = new PartidoDAO();
    EleitorDAO eleitorDAO = new EleitorDAO();
    EleicaoDAO eleicaoDAO = new EleicaoDAO();

    public boolean incluirCandidato(Connection conn, Candidato candidato) throws SQLException {

        boolean sucesso = false;

        String sql = "INSERT INTO tb_candidato( \n"
                + "	id_partido,\n"
                + "	nome_candidato,\n"
                + "	numero_candidato,\n"
                + " id_eleicao) values\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "'?');\n";

        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);

            stm.setInt(1, candidato.getPartido().getId_partido());
            stm.setString(2, candidato.getNomeCandidato());
            stm.setInt(3, candidato.getNumeroCandidato());
            stm.setInt(4, candidato.getIdEleicao());
            stm.executeUpdate();
            sucesso = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            //ex.printStackTrace();        
            //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            stm.close();

        }

        return sucesso;
    }

    public boolean removerCandidato(Connection conn, int idCandidato) throws SQLException {

        boolean sucesso = false;

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

        }

        return sucesso;
    }

    public List<Candidato> listarCandidatos(Connection conn, String sql) throws SQLException {

        Statement stm = null;
        ResultSet rs = null;
        List<Candidato> listaCandidatos = new ArrayList<Candidato>();
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {

                Candidato candidato = new Candidato();
                int idCanditado = rs.getInt("id_candidato");
                int numeroCandidato = rs.getInt("numero_candidato");
                Partido partido = partidoDAO.buscaById(conn, rs.getInt("id_partido"));
                String nomeCandidato = rs.getString("nome_candidato");
                int idEleicao = rs.getInt("id_eleicao");
                

                candidato.setIdCandidato(idCanditado);
                candidato.setNumeroCandidato(numeroCandidato);
                candidato.setNomeCandidato(nomeCandidato);
                candidato.setPartido(partido);
                candidato.setIdEleicao(idEleicao);

                listaCandidatos.add(candidato);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stm.close();
            rs.close();

        }

        return listaCandidatos;

    }

    public List<Candidato> listarTodosCandidatos(Connection conn) throws SQLException {

        Statement stm = conn.createStatement();
        String sql = "SELECT * FROM tb_candidato ORDER BY id_candidato";
        ResultSet rs = stm.executeQuery(sql);

        List<Candidato> listaCandidatos = new ArrayList<Candidato>();

        while (rs.next()) {

                Candidato candidato = new Candidato();
                int idCanditado = rs.getInt("id_candidato");
                int numeroCandidato = rs.getInt("numero_candidato");
                Partido partido = partidoDAO.buscaById(conn, rs.getInt("id_partido"));
                String nomeCandidato = rs.getString("nome_candidato");
                int idEleicao = rs.getInt("id_eleicao");
                

                candidato.setIdCandidato(idCanditado);
                candidato.setNumeroCandidato(numeroCandidato);
                candidato.setNomeCandidato(nomeCandidato);
                candidato.setPartido(partido);
                candidato.setIdEleicao(idEleicao);

            listaCandidatos.add(candidato);
        }

        stm.close();
        rs.close();

        return listaCandidatos;

    }

    public Candidato buscarCandidato(Connection conn, int idCandidato) throws SQLException {

        String sql = "SELECT * FROM tb_candidato WHERE id_candidato = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, idCandidato);

        ResultSet rs = stm.executeQuery(sql);
        Candidato candidato = null;

        while (rs.next()) {

                candidato = new Candidato();
                int idCanditado = rs.getInt("id_candidato");
                int numeroCandidato = rs.getInt("numero_candidato");
                Partido partido = partidoDAO.buscaById(conn, rs.getInt("id_partido"));
                String nomeCandidato = rs.getString("nome_candidato");
                int idEleicao = rs.getInt("id_eleicao");
                

                candidato.setIdCandidato(idCanditado);
                candidato.setNumeroCandidato(numeroCandidato);
                candidato.setNomeCandidato(nomeCandidato);
                candidato.setPartido(partido);
                candidato.setIdEleicao(idEleicao);

        }

        stm.close();
        rs.close();

        return candidato;

    }

    Candidato buscarCandidatoByNumeroCandidato(Connection conn, int numeroCandidato) throws SQLException {

        String sql = "SELECT * FROM tb_candidato WHERE numero_candidato = " + numeroCandidato;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        Candidato candidato = null;

        while (rs.next()) {

                candidato = new Candidato();
                int idCanditado = rs.getInt("id_candidato");
                Partido partido = partidoDAO.buscaById(conn, rs.getInt("id_partido"));
                String nomeCandidato = rs.getString("nome_candidato");
                int idEleicao = rs.getInt("id_eleicao");
                

                candidato.setIdCandidato(idCanditado);
                candidato.setNumeroCandidato(numeroCandidato);
                candidato.setNomeCandidato(nomeCandidato);
                candidato.setPartido(partido);
                candidato.setIdEleicao(idEleicao);

        }

        stm.close();
        rs.close();

        return candidato;

    }

}
