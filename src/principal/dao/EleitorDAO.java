/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import principal.model.Eleitor;

/**
 *
 * @author Emm
 */
public class EleitorDAO extends ConexaoDAO {


    Eleitor buscaByID(int idEleitor) throws SQLException {

        Eleitor eleitor = new Eleitor();
        
        
        Connection conn = this.criaConexao();
        Statement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM tb_eleitor WHERE id_eleitor = " + idEleitor;

        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                eleitor.setId_Eleitor(rs.getInt("id_eleitor"));
                eleitor.setIdade(rs.getInt("idade"));
                eleitor.setNome(rs.getString("nome"));
                eleitor.setRG(rs.getInt("RG"));
                eleitor.setTituloEleitor(rs.getInt("titulo_eleitor"));

            }

        } catch (SQLException ex) {
            
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stmt.close();
            rs.close();
            this.fecharConexao(conn);
        }

        return eleitor;

    }

    public Eleitor buscaEleitorByRG(String rgEleitor) throws SQLException {


        Eleitor eleitor = new Eleitor();
        
        
        Connection conn = this.criaConexao();
        Statement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM tb_eleitor WHERE RG = '"+rgEleitor+"';";

        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                eleitor.setId_Eleitor(rs.getInt("id_eleitor"));
                eleitor.setIdade(rs.getInt("idade"));
                eleitor.setNome(rs.getString("nome"));
                eleitor.setRG(rs.getInt("RG"));
                eleitor.setTituloEleitor(rs.getInt("titulo_eleitor"));

            }

        } catch (SQLException ex) {
            
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stmt.close();
            rs.close();
            this.fecharConexao(conn);
        }

        return eleitor;

    }

        
        
        
        
}
