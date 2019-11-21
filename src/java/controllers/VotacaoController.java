/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CandidatoDAO;
import dao.EleicaoDAO;
import dao.EleitorDAO;
import dao.VotoDAO;
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
import model.Candidato;
import model.Eleicao;
import model.Eleitor;
import model.Voto;

/**
 *
 * @author Emm
 */
@WebServlet(urlPatterns = {"/votacao"}, loadOnStartup = 0)
public class VotacaoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CandidatoDAO candidatoDAO = new CandidatoDAO();
    EleicaoDAO eleicaoDAO = new EleicaoDAO();
    EleitorDAO eleitorDAO = new EleitorDAO();
    String stringErro = null;
    String stringSucesso = null;
    List<Candidato> listaCandidatos = null;
    List<Eleicao> listaEleicoes = null;
    

    /*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        PrintWriter out = response.getWriter();
        out.write(request.getContextPath());
        
        
    }
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Candidato> listaCandidatos = (List<Candidato>) request.getAttribute("listaCandidatos");
        List<Eleicao> listaEleicoes = (List<Eleicao>) request.getAttribute("listaEleicoes");

        if (listaCandidatos == null && listaEleicoes == null) {

            try {
                this.listaEleicoes = eleicaoDAO.listarEleicoes();
            } catch (SQLException ex) {
                Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                String idEleicaoSelecionada = request.getParameter("id_eleicao_selecionado");
                if (idEleicaoSelecionada != null) {
                    this.listaCandidatos = eleicaoDAO.listaCandidatosByEleicao(Integer.parseInt(idEleicaoSelecionada));
                } else {
                    this.listaCandidatos = candidatoDAO.listarTodosCandidatos();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        request.setAttribute("listaCandidatos", this.listaCandidatos);
        request.setAttribute("listaEleicoes", this.listaEleicoes);
        request.setAttribute("stringErro", stringErro);
        request.setAttribute("stringSucesso", stringSucesso);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/votacao.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idEleicao = Integer.parseInt(request.getParameter("idEleicao"));
        String candidato = request.getParameter("painel");

        int numeroCandidato = 0;

        if (!candidato.isEmpty()) {
            numeroCandidato = Integer.parseInt(candidato);
        }

        String rgEleitor = null;

        try {

            rgEleitor = request.getParameter("identificador");

        } catch (Exception ex) {

            ex.printStackTrace();
            stringSucesso = null;
            stringErro = "Erro no preenchimento do RG";

        }
        Eleitor eleitor = null;
        try {

            eleitor = eleitorDAO.buscaEleitorByRG(rgEleitor);
            if (eleitor == null) {

                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/eleitor.jsp");

                dispatcher.forward(request, response);
                return;

            }
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

        if (voto != null) {

            try {

                throw new Exception();

            } catch (Exception ex) {

                stringErro = "Já existe um voto deste eleitor para esta eleição";
                stringSucesso = null;
                this.doGet(request, response);
                Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

        } else {

            boolean sucesso = false;
            boolean coincide = true;

            try {
                if (numeroCandidato != 0) {
                    coincide = candidatoDAO.verificaCandidatoEleicao(idEleicao, numeroCandidato);
                }
            } catch (SQLException ex) {
                Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!coincide) {
                try {
                    throw new Exception();
                } catch (Exception ex) {
                    stringErro = "O candidato não está registrado para esta eleição";
                    stringSucesso = null;
                    this.doGet(request, response);
                    Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            } else {

                if (numeroCandidato != 0) {
                    try {

                        sucesso = votoDAO.inserirVoto(eleitor.getId_Eleitor(), idEleicao, numeroCandidato);

                    } catch (SQLException ex) {
                        Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (numeroCandidato == 0) {
                    try {

                        sucesso = votoDAO.inserirVotoNulo(eleitor.getId_Eleitor(), idEleicao);

                    } catch (SQLException ex) {
                        Logger.getLogger(VotacaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            if (sucesso) {
                stringErro = null;
                stringSucesso = "Voto inserido no sistema com sucesso !";
                this.doGet(request, response);

            } else {

                stringErro = "Houve um erro ao inserir o seu voto. Tente novamente !";
                stringSucesso = null;
                this.doGet(request, response);
            }

        }

    }

}
