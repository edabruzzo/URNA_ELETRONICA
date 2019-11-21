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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Eleitor;

/**
 *
 * @author Emm
 */
public class EleitorDAO {
    
    
    ConexaoDAO conexaoDAO = new ConexaoDAO();

    Eleitor buscaByID(int idEleitor) throws SQLException {

        Eleitor eleitor = new Eleitor();

        Connection conn = conexaoDAO.criaConexao();
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
            conexaoDAO.fecharConexao(conn);
        }

        return eleitor;

    }

    public Eleitor buscaEleitorByRG(String rgEleitor) throws SQLException {

        Connection conn = conexaoDAO.criaConexao();
        Eleitor eleitor = null;

        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM tb_eleitor WHERE RG = '" + rgEleitor + "';";

        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                eleitor = new Eleitor();
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
            conexaoDAO.fecharConexao(conn);
        }

        return eleitor;

    }

    public String criarEleitor(Eleitor eleitor) throws SQLException {

        Connection conn = conexaoDAO.criaConexao();
        String mensagem = null;
        String sql = "INSERT INTO tb_eleitor (nome, idade, RG, titulo_eleitor) \n"
                + "values(?, ?, ?, ?);";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, eleitor.getNome());
            stmt.setInt(2, eleitor.getIdade());
            stmt.setInt(3, eleitor.getRG());
            stmt.setInt(4, 0);
            stmt.execute();

            mensagem = "Eleitor inserido no banco com sucesso";

        } catch (SQLException ex) {
            
            if(ex.getMessage().contains("duplicate key value violates unique constraint \"tb_eleitor_rg_key\""))
            
                mensagem = "Já existe usuário com o mesmo número de RG";
            
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            stmt.close();
            conexaoDAO.fecharConexao(conn);
        }
        
        return mensagem;
    }

}
