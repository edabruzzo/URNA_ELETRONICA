/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ConexaoDAO;
import java.io.IOException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emm
 */
@WebServlet(urlPatterns = {"/criarInfra"}, loadOnStartup = 1)
public class CriarInfraController extends HttpServlet {
    


private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                ConexaoDAO conexao = new ConexaoDAO();
                try{
    
                  conexao.criaInfraestruturaPOSTGRES_ARQUIVO();
//                conexao.criaInfraestrutura_POSTGRES();
  //              conexao.criaInfraestrutura_MYSQL();
                    
                }catch(Exception e){
                    System.out.println(e);
                }

        
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
        
    }




}
