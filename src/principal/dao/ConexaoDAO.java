/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emm
 */
public class ConexaoDAO {

    private String URL;
    private String USER;
    private String PASSWORD;

    

    /*            
            POSTGRESQL : LOCALIZAÇÃO DO ARQUIVO pgpass.conf
            /c/users/emm/appdata/roaming/postgresql
            
            INICIALIZAÇÃO DO POSTGRESQL
            C:\Program Files (x86)\PostgreSQL\9.6\bin>psql -U postgres

     */
    public Connection criaConexao() {

        setURL("jdbc:postgresql://localhost:5432/urna");
        setUSER("postgres");
        setPASSWORD(System.getenv("POSTGRES_PASSWORD"));
        
        Connection conn = null;

        System.out.println("Conectando ao servidor com a seguinte URL : " + this.URL);

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;

    }

    public void fecharConexao(Connection conn) {

        System.out.println("Fechando a conexão com o banco de dados");
        try {
            if (conn != null) {
                conn.close();

                System.out.println("Conexão com o banco de dados fechada com sucesso");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("********ATENÇÃO ! Conexão com o banco de dados não foi fechada !");

        }
    }

    public void criaInfraestrutura_MYSQL() {

        setURL("jdbc:mysql://localhost:3306/URNA?useTimezone=true&serverTimezone=UTC");
        setUSER("root");
        setPASSWORD("root");

        ArrayList<String> listaSQLs = new ArrayList();

        String sql1 = "CREATE TABLE IF NOT EXISTS tb_partido(\n"
                + "	id_partido INT AUTO_INCREMENT NOT NULL,\n"
                + "	nomeCompleto VARCHAR(255) NOT NULL,\n"
                + "	sigla VARCHAR(10),\n"
                + "	PRIMARY KEY(id_partido)\n"
                + ");";

        listaSQLs.add(sql1);

        String sql2 = "CREATE TABLE IF NOT EXISTS tb_eleitor(\n"
                + "\n"
                + "	id_eleitor INT AUTO_INCREMENT NOT NULL,\n"
                + "	nome VARCHAR(255) NOT NULL,\n"
                + "	idade INT NOT NULL,\n"
                + "	tituloEleitor int not null,\n"
                + "	RG INT NOT NULL,\n"
                + "\n"
                + "	PRIMARY KEY(id_eleitor)\n"
                + "\n"
                + "\n"
                + ");\n"
                + "";
        listaSQLs.add(sql2);

        String sql3 = "CREATE TABLE IF NOT EXISTS tb_candidato(\n"
                + "	id_candidato int AUTO_INCREMENT NOT NULL, \n"
                + "	id_partido int NOT NULL\n"
                + "	nome VARCHAR(255) NOT NULL,\n"
                + "	estadoFederacao VARCHAR(2) NOT NULL,\n"
                + "	\n"
                + "	PRIMARY KEY (id_candidato),\n"
                + "	FOREIGN KEY (id_partido) REFERENCES tb_partido(id_partido)\n"
                + "	\n"
                + ");\n"
                + "";

        listaSQLs.add(sql3);

        Connection conn = null;
        ResultSet rs = null;

        conn = this.criaConexao();

        try {
            executaBatchUpdate(conn, listaSQLs);
        } catch (SQLException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //https://www.guj.com.br/t/executar-arquivo-sql-usando-jdbc/135796/2
    public void criaInfraestruturaPOSTGRES_ARQUIVO() {

        setURL("jdbc:postgresql://localhost:5432/urna");
        setUSER("postgres");
        setPASSWORD(System.getenv("POSTGRES_PASSWORD"));

        File arquivoInfra = new File(System.getProperty("user.dir").concat("/ScriptsSQL/"
                + "criaInfraestrutura_POSTGRES.sql"));

        String delimitador = ";";
        Scanner scanner = null;

        try {

            scanner = new Scanner(arquivoInfra).useDelimiter(delimitador);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        Connection conn = null;
        conn = this.criaConexao();
        Statement stmt = null;

        while (scanner.hasNext()) {

            String sqlParcialBruto = scanner.next().concat(delimitador);
            try {
                System.out.println("Executando o sql ...\n");
                System.out.println(sqlParcialBruto);
                System.out.println("\n");
                stmt = conn.createStatement();
                stmt.executeUpdate(sqlParcialBruto);
                System.out.println("Query executada com sucesso");

            } catch (SQLException ex) {
                ex.printStackTrace();
                ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fecharConexao(conn);

    }

    public void criaInfraestrutura_POSTGRES() {

        setURL("jdbc:postgresql://localhost:5432/urna");
        setUSER("postgres");
        setPASSWORD(System.getenv("POSTGRES_PASSWORD"));

        ArrayList<String> listaSQLs = new ArrayList();

        String sql1 = "\n"
                + "CREATE TABLE IF NOT EXISTS tb_partido(\n"
                + "	id_partido SERIAL PRIMARY KEY,\n"
                + "	nomeCompleto VARCHAR(255) NOT NULL,\n"
                + "	sigla VARCHAR(10)\n"
                + ");\n"
                + "";

        listaSQLs.add(sql1);

        String sql2 = "CREATE TABLE IF NOT EXISTS tb_eleitor(\n"
                + "\n"
                + "	id_eleitor SERIAL PRIMARY KEY,\n"
                + "	nome VARCHAR(255) NOT NULL,\n"
                + "	idade INT NOT NULL,\n"
                + "	tituloEleitor int not null,\n"
                + "	RG INT NOT NULL\n"
                + "\n"
                + "\n"
                + ");\n"
                + "";
        listaSQLs.add(sql2);

        String sql3 = "CREATE TABLE IF NOT EXISTS tb_candidato(\n"
                + "	id_candidato SERIAL PRIMARY KEY , \n"
                + "	numero_candidato INT, \n"
                + "	id_partido int NOT NULL REFERENCES tb_partido(id_partido),\n"
                + "	nome VARCHAR(255) NOT NULL,\n"
                + "	estadoFederacao VARCHAR(10) NOT NULL\n"
                + "	\n"
                + ");\n"
                + "\n"
                + "";

        listaSQLs.add(sql3);

        Connection conn = null;
        ResultSet rs = null;

        conn = this.criaConexao();

        try {
            executaBatchUpdate(conn, listaSQLs);
        } catch (SQLException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void executaBatchUpdate(Connection conn, ArrayList<String> listaSQLs) throws SQLException, ClassNotFoundException {

        Statement stmt = conn.createStatement();
        conn.setAutoCommit(false);

        for (String sql : listaSQLs) {

            System.out.println(sql);
            stmt.addBatch(sql);

        }

        try {
            System.out.println("Executando commit em lote .....");
            stmt.executeBatch();
            conn.commit();
            System.out.println("Executada com sucesso!");

        } catch (SQLException ex) {

            System.out.println("Query não executada! Efetuando rollback");
            conn.rollback();
            ex.printStackTrace();        //Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stmt.close();
            fecharConexao(conn);
        }
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

}
