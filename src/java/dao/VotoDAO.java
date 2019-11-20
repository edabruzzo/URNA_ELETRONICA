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
import model.Voto;

/**
 *
 * @author Emm
 */
public class VotoDAO  {
    
    CandidatoDAO candidatoDAO = new CandidatoDAO();

    List<Voto> listarVotos(Connection conn, String sql) throws SQLException {

        List<Voto> listaVotos = new ArrayList<Voto>();
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
        }
        return listaVotos;

    }

    List<Voto> listarTodosVotos(Connection conn) throws SQLException {

        List<Voto> listaVotos = new ArrayList<Voto>();
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
        }
        return listaVotos;

    }

    public Voto pesquisarVotoByIdEleicaoIdEleitor(Connection conn, int idEleicao, int idEleitor) throws SQLException {

        Statement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_voto WHERE id_eleicao = " + idEleicao + " "
                + "AND id_eleitor = " + idEleitor + ";";
        Voto voto = null;

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                voto = new Voto();    
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
        }
        return voto;

    }

    public boolean inserirVoto(Connection conn, int idEleitor, int idEleicao, int numeroCandidato) throws SQLException {
    
     boolean sucesso = false;

        String sql = "INSERT INTO tb_voto( \n"
                + "	id_candidato,\n"
                + "	id_eleitor,\n"
                + "	id_eleicao)values\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "?);\n";

        
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            
            Candidato candidato = candidatoDAO.buscarCandidatoByNumeroCandidato(conn, numeroCandidato);
            
            if(candidato == null){
                try {
                    throw new Exception();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
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

        }

        return sucesso;
    
    }




    public boolean inserirVotoNulo(Connection conn, int idEleitor, int idEleicao) throws SQLException {
    
     boolean sucesso = false;

        String sql = "INSERT INTO tb_voto( \n"
                + "	id_candidato,\n"
                + "	id_eleitor,\n"
                + "	id_eleicao)values\n"
                + "(\n"
                + "null,\n"
                + "?,\n"
                + "?);\n";

        
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, idEleitor);
            stm.setInt(2, idEleicao);
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


}
