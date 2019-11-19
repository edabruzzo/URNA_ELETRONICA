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
import model.Candidato;
import model.Voto;

/**
 *
 * @author Emm
 */
public class VotoDAO extends ConexaoDAO {
    
    CandidatoDAO candidatoDAO = new CandidatoDAO();

    List<Voto> listarVotos(String sql) throws SQLException {

        List<Voto> listaVotos = new ArrayList<Voto>();
        Connection conn = this.criaConexao();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                Voto voto = new Voto();
                voto.setId_voto(rs.getInt("id_voto"));
                voto.setId_candidato(rs.getInt("id_candidato"));
                voto.setId_eleicao(rs.getInt("id_eleicao"));
                voto.setId_eleitor(rs.getInt("id_eleitor"));

                listaVotos.add(voto);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            statement.close();
            rs.close();
            this.fecharConexao(conn);
        }
        return listaVotos;

    }

    List<Voto> listarTodosVotos() throws SQLException {

        List<Voto> listaVotos = new ArrayList<Voto>();
        Connection conn = this.criaConexao();
        Statement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_voto;";
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                Voto voto = new Voto();
                voto.setId_voto(rs.getInt("id_voto"));
                voto.setId_candidato(rs.getInt("id_candidato"));
                voto.setId_eleicao(rs.getInt("id_eleicao"));
                voto.setId_eleitor(rs.getInt("id_eleitor"));

                listaVotos.add(voto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            statement.close();
            rs.close();
            this.fecharConexao(conn);
        }
        return listaVotos;

    }

    public Voto pesquisarVotoByIdEleicaoIdEleitor(int idEleicao, int idEleitor) throws SQLException {

        Connection conn = this.criaConexao();
        Statement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_voto WHERE id_eleicao = " + idEleicao + " "
                + "AND id_eleitor = " + idEleitor + ";";
        Voto voto = new Voto();

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                voto.setId_voto(rs.getInt("id_voto"));
                voto.setId_candidato(rs.getInt("id_candidato"));
                voto.setId_eleicao(rs.getInt("id_eleicao"));
                voto.setId_eleitor(rs.getInt("id_eleitor"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            statement.close();
            rs.close();
            this.fecharConexao(conn);
        }
        return voto;

    }

    public boolean inserirVoto(int idEleitor, int idEleicao, int numeroCandidato) throws SQLException {
    
     boolean sucesso = false;

        Connection conn = this.criaConexao();
        String sql = "INSERT INTO tb_voto( \n"
                + "	id_candidato,\n"
                + "	id_eleitor,\n"
                + "	id_eleicao)values\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "'?');\n";

        
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            
            Candidato candidato = candidatoDAO.buscarCandidatoByNumeroCandidato(numeroCandidato);
            
            
            stm.setInt(1, candidato.getIdCandidato());
            stm.setInt(2, idEleitor);
            stm.setInt(3, idEleicao);
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

}
