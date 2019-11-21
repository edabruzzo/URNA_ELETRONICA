/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import principal.dao.EleitorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import principal.model.Eleitor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emm
 */
@WebServlet(name = "EleitorController", urlPatterns = {"/eleitor"})
public class EleitorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
    String corMensagem = "white";
    String mensagem = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
  
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EleitorController</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet EleitorController at " + request.getContextPath() + "</h1>");
            out.print("<h4 style='color: "+corMensagem+"'> "+mensagem+"</h4>");
            out.print("<a href=\""+request.getContextPath()+"/votacao\">Voltar</a>\n");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    EleitorDAO eleitorDAO = new EleitorDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    
            
            Eleitor eleitor = new Eleitor();
            String mensagem = "";
            
            String nome = request.getParameter("nome");
            int idade = Integer.parseInt(request.getParameter("idade"));
            int rg = Integer.parseInt(request.getParameter("rg"));
            
            
            eleitor.setNome(nome);
            eleitor.setIdade(idade);
            eleitor.setRG(rg);
            eleitor.setTituloEleitor(0);
        try {
            mensagem = eleitorDAO.criarEleitor(eleitor);
            this.mensagem = mensagem;
            this.corMensagem = "green";
            
            if(mensagem.contains("mesmo número de RG"))
                corMensagem = "red";
            
            this.processRequest(request, response);

    
        } catch (SQLException ex) {
            Logger.getLogger(EleitorController.class.getName()).log(Level.SEVERE, null, ex);
                        
            
    }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
