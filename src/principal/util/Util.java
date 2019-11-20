/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.util;

import principal.dao.ConexaoDAO;

import java.util.Scanner;

/**
 *
 * @author Emm
 */
public class Util {

    public static void main(String[] args) {

        Util.criarInfra();
//        System.out.println(System.getProperty("user.dir"));
    }

    public static void criarInfra() {
        ConexaoDAO conexao = new ConexaoDAO();
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Digite 1 para Mysql e 2 para Postgres...");
        int opcaoBanco = sc1.nextInt();

        if (opcaoBanco == 1) {
        
            //conexao.criaInfraestrutura_MYSQL();
        
        } else if (opcaoBanco == 2) {
            //conexao.criaInfraestrutura_POSTGRES();
            conexao.criaInfraestruturaPOSTGRES_ARQUIVO();
        } else {
            System.out.println("Opção errada");
        }

    }
}
