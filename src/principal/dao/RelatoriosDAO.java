/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.dao;

/**
 *
 * @author MARTIN MARIANO
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;

public class RelatoriosDAO extends ConexaoDAO {


    public HashMap<String, ArrayList<SimpleEntry<String, Integer>>> listarVotosPorPartido() throws SQLException {


        HashMap<String, ArrayList<SimpleEntry<String, Integer>>> votosPorPartido = new HashMap<>();
        Connection conn = this.criaConexao();
        Statement statement = null;

        String sql = "SELECT COUNT(*) AS Votos_Totais,\n" +
                "COALESCE(INITCAP(P.nomecompleto), 'BRANCO') AS Nome_Partido,\n" +
                "InitCap(E.cargo) AS Cargo\n" +
                "FROM public.tb_voto AS V\n" +
                "LEFT JOIN tb_candidato AS C ON V.id_candidato = C.id_candidato\n" +
                "LEFT JOIN tb_partido AS P ON C.id_partido = P.id_partido\n" +
                "INNER JOIN tb_eleicao AS E ON E.id_eleicao = V.id_eleicao\n" +
                "GROUP BY P.nomecompleto, E.cargo\n" +
                "ORDER BY E.cargo, COUNT(*);";

        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String nome_partido = rs.getString("Nome_Partido");
                int votos_totais = rs.getInt("Votos_Totais");
                String cargo = rs.getString("cargo");

                votosPorPartido.putIfAbsent(cargo, new ArrayList<>());
                votosPorPartido.get(cargo).add(new SimpleEntry<>(nome_partido, votos_totais));

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


    public HashMap<String, ArrayList<SimpleEntry<String, Integer>>> listarVotosPorCandidato() throws SQLException {

        HashMap<String, ArrayList<SimpleEntry<String, Integer>>> votosPorCandidato = new HashMap<>();
        Connection conn = this.criaConexao();
        Statement statement = null;

        String sql = "SELECT COUNT(*) AS Votos_Totais,\n" +
                "COALESCE(InitCap(C.nome_candidato), 'BRANCO') AS Nome_Candidato,\n" +
                "InitCap(E.cargo) AS Cargo\n" +
                "FROM public.tb_voto AS V\n" +
                "LEFT JOIN tb_candidato AS C ON V.id_candidato = C.id_candidato\n" +
                "INNER JOIN tb_eleicao AS E ON E.id_eleicao = V.id_eleicao\n" +
                "GROUP BY E.cargo, C.nome_candidato\n" +
                "ORDER BY COUNT(*)";

        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String nome_candidato = rs.getString("Nome_Candidato");
                int votos_totais = rs.getInt("Votos_Totais");
                String cargo = rs.getString("cargo");

                votosPorCandidato.putIfAbsent(cargo, new ArrayList<>());
                votosPorCandidato.get(cargo).add(new SimpleEntry<>(nome_candidato, votos_totais));
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
