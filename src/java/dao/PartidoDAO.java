/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Partido;

/**
 *
 * @author Emm
 */
public class PartidoDAO extends ConexaoDAO{

    Partido buscaById(int idPartido) throws SQLException {
     
    Connection conn = this.criaConexao();
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM tb_partido WHERE id_partido = "+idPartido;
    Partido partido = new Partido();
        try {
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                partido.setId_partido(rs.getInt("id_partido"));
                partido.setNumeroPartido(rs.getInt("numero_partido"));
                partido.setNomeCompleto(rs.getString("nomeCompleto"));
                partido.setSigla(rs.getString("sigla"));
                
            }
        
        
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(PartidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            stmt.close();
            rs.close();
            this.fecharConexao(conn);
            
        }
        
        return partido;
    
    
    }
    
}
