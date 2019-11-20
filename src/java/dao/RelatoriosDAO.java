/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author MARTIN MARIANO
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class RelatoriosDAO extends ConexaoDAO {


    public HashMap<String, Integer> listarVotosPorPartido() throws SQLException {

        HashMap<String, Integer> votosPorPartido = new HashMap<>();
        Connection conn = this.criaConexao();
        Statement statement = null;

        String sql = "select COUNT(*) AS Votos_Totais,\n" +
                "P.nomecompleto AS Nome_Partido\n" +
                "FROM public.tb_voto AS V\n" +
                "INNER JOIN tb_candidato AS C ON V.id_candidato = C.id_candidato\n" +
                "INNER JOIN tb_partido AS P ON C.id_partido = P.id_partido\n" +
                "GROUP BY P.nomecompleto\n" +
                "ORDER BY COUNT(*);";

        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                votosPorPartido.put(rs.getString("Nome_Partido"), rs.getInt("Votos_Totais"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            statement.close();
            rs.close();
            this.fecharConexao(conn);
        }
        return votosPorPartido;
    }


    public HashMap<String, Integer> listarVotosPorCandidato() throws SQLException {

        HashMap<String, Integer> votosPorCandidato = new HashMap<>();
        Connection conn = this.criaConexao();
        Statement statement = null;

        String sql = "SELECT COUNT(*) AS Votos_Totais,\n" +
                "       C.nome_candidato AS Nome_Candidato\n" +
                "FROM public.tb_voto AS V\n" +
                "INNER JOIN tb_candidato AS C ON V.id_candidato = C.id_candidato\n" +
                "GROUP BY C.nome_candidato\n" +
                "ORDER BY COUNT(*)";

        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                votosPorCandidato.put(rs.getString("Nome_Candidato"), rs.getInt("Votos_Totais"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            statement.close();
            rs.close();
            this.fecharConexao(conn);
        }
        return votosPorCandidato;
    }
}
