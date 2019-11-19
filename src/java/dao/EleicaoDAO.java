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
import model.Voto;

/**
 *
 * @author Emm
 */
public class EleicaoDAO extends ConexaoDAO {

    public List<Eleicao> listarEleicoes() throws SQLException {

        List<Eleicao> listaEleicoes = new ArrayList<Eleicao>();
        Connection conn = this.criaConexao();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM tb_eleicao";
        ResultSet rs = stmt.executeQuery(sql);

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Eleicao eleicao = new Eleicao();

                eleicao.setCargo(rs.getString("cargo"));
                eleicao.setId_eleicao(rs.getInt("id_eleicao"));
                eleicao.setListaCandidatos(this.listaCandidatosByEleicao(eleicao.getId_eleicao()));
                eleicao.setUnidadeFederacao("unidade_federacao");
                eleicao.setVotos(this.listarVotosByEleicao(eleicao.getId_eleicao()));

                listaEleicoes.add(eleicao);
            }

        } catch (SQLException ex) {
            
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stmt.close();
            rs.close();

        }

        return listaEleicoes;
    }

    
    
    private List<Candidato> listaCandidatosByEleicao(int idEleicao) throws SQLException {

        String sql = "SELECT c.* FROM tb_eleicao_candidato e\n"
                + " INNER JOIN tb_candidato c ON c.id_candidato = e.id_candidato \n"
                + "WHERE id_eleicao = " + idEleicao;

        CandidatoDAO candidatoDAO = new CandidatoDAO();

        return candidatoDAO.listarCandidatos(sql);

    }

    private List<Voto> listarVotosByEleicao(int idEleicao) throws SQLException {

        String sql = "SELECT * FROM tb_voto WHERE id_eleicao = " + idEleicao;
        VotoDAO votoDAO = new VotoDAO();

        return votoDAO.listarVotos(sql);

    }

}
