package controllers;

import dao.RelatoriosDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Relatorios", urlPatterns = {"/relatorios"})
public class RelatoriosController extends HttpServlet {

    RelatoriosDAO relatorioDAO = new RelatoriosDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, Integer> listaVotosPorPartido = null;
        HashMap<String, Integer> listaVotosPorCandidato = null;
        
        try {
            listaVotosPorPartido = relatorioDAO.listarVotosPorPartido();
            listaVotosPorCandidato = relatorioDAO.listarVotosPorCandidato();

        } catch (SQLException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        request.setAttribute("listaVotosPorCandidato", listaVotosPorCandidato);
        request.setAttribute("listaVotosPorPartido", listaVotosPorPartido);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/relatorios.jsp");
        dispatcher.forward(request, response);

    }
}