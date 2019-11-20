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
import java.util.ArrayList;
import java.util.List;
import principal.model.Candidato;
import principal.model.Eleicao;
import principal.model.Voto;

/**
 *
 * @author Emm
 */
public class EleicaoDAO extends ConexaoDAO {

    public List<Eleicao> listarEleicoes() throws SQLException {

        List<Eleicao> listaEleicoes = new ArrayList<Eleicao>();
        Connection conn = this.criaConexao();
        Statement stmt = null;
        String sql = "SELECT * FROM tb_eleicao";
        ResultSet rs = null;

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
            this.fecharConexao(conn);

        }

        return listaEleicoes;
    }



    private List<Candidato> listaCandidatosByEleicao(int idEleicao) throws SQLException {

        String sql = "SELECT c.* FROM tb_candidato c \n"
                + "WHERE id_eleicao = " + idEleicao;

        CandidatoDAO candidatoDAO = new CandidatoDAO();

        return candidatoDAO.listarCandidatos(sql);

    }

    private List<Voto> listarVotosByEleicao(int idEleicao) throws SQLException {

        String sql = "SELECT * FROM tb_voto WHERE id_eleicao = " + idEleicao;
        VotoDAO votoDAO = new VotoDAO();

        return votoDAO.listarVotos(sql);

    }

    public Eleicao buscaById(int idEleicao) throws SQLException {

        Connection conn = this.criaConexao();
        Statement stmt = null;
        String sql = "SELECT * FROM tb_eleicao WHERE id_eleicao = "+idEleicao;
        ResultSet rs = null;
        Eleicao eleicao = null;
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                eleicao = new Eleicao();

                eleicao.setCargo(rs.getString("cargo"));
                eleicao.setId_eleicao(rs.getInt("id_eleicao"));
                eleicao.setListaCandidatos(this.listaCandidatosByEleicao(eleicao.getId_eleicao()));
                eleicao.setUnidadeFederacao("unidade_federacao");
                eleicao.setVotos(this.listarVotosByEleicao(eleicao.getId_eleicao()));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            stmt.close();
            rs.close();
            this.fecharConexao(conn);
        }

        return eleicao;


    }

}
