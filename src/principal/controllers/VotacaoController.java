/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controllers;

import principal.dao.CandidatoDAO;
import principal.dao.EleicaoDAO;
import principal.dao.EleitorDAO;
import principal.dao.VotoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import principal.model.Candidato;
import principal.model.Eleicao;
import principal.model.Eleitor;
import principal.model.Voto;

/**
 *
 * @author Emm
 */
@WebServlet(name = "Votacao", urlPatterns = {"/votacao"}, loadOnStartup = 0)
public class VotacaoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

        CandidatoDAO candidatoDAO = new CandidatoDAO();
        EleicaoDAO eleicaoDAO = new EleicaoDAO();
        EleitorDAO eleitorDAO = new EleitorDAO();
        String stringErro = null;
        String stringSucesso = null;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        List<Candidato> listaCandidatos = null;
        List<Eleicao> listaEleicoes = null;

        try {

            listaCandidatos = candidatoDAO.listarTodosCandidatos();
            listaEleicoes = eleicaoDAO.listarEleicoes();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listaCandidatos", listaCandidatos);
        request.setAttribute("listaEleicoes", listaEleicoes);
        request.setAttribute("stringErro", stringErro);
        request.setAttribute("stringSucesso", stringSucesso);
        
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/votacao.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idEleicao = Integer.parseInt(request.getParameter("idEleicao"));
        int numeroCandidato = Integer.parseInt(request.getParameter("painel"));
        String rgEleitor = null;
        
        try{

           rgEleitor = request.getParameter("identificador");
        
        }catch(Exception ex){
            
            ex.printStackTrace();
            stringSucesso = null;
            stringErro = "Erro no preenchimento do RG";
            
        }
        Eleitor eleitor = new Eleitor();
        try {
            eleitor = eleitorDAO.buscaEleitorByRG(rgEleitor);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        VotoDAO votoDAO = new VotoDAO();
        
        Voto voto = null;
                
        try {
            voto = votoDAO.pesquisarVotoByIdEleicaoIdEleitor(idEleicao, eleitor.getId_Eleitor());
        } catch (SQLException ex) {
            Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (voto != null){
            
            try {
                
                throw new Exception();
                
            } catch (Exception ex) {
                
                stringErro = "Já existe um voto deste eleitor para esta eleição";
                stringSucesso = null;
                this.doGet(request, response);
                Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            
         boolean sucesso = false;
            try {
                sucesso = votoDAO.inserirVoto(eleitor.getId_Eleitor(), idEleicao, numeroCandidato);
            } catch (SQLException ex) {
                Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         if(sucesso){
             stringErro = null;
             stringSucesso = "Voto inserido no sistema com sucesso !";
             this.doGet(request, response);
             
             
         }else{
             
             stringErro = "Houve um erro ao inserir o seu voto. Tente novamente !";
             stringSucesso = null;
             this.doGet(request, response);
         }
         
         
        }
        
        

    }

}
