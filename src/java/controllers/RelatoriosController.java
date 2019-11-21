package controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.RelatoriosDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "RelatoriosController", urlPatterns = {"/relatorios"})
public class RelatoriosController extends HttpServlet {

    RelatoriosDAO relatorioDAO = new RelatoriosDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, ArrayList<SimpleEntry<String, Integer>>> listaVotosPorPartido = null;
        HashMap<String, ArrayList<SimpleEntry<String, Integer>>> listaVotosPorCandidato = null;

        try {
            listaVotosPorPartido = relatorioDAO.listarVotosPorPartido();
            listaVotosPorCandidato = relatorioDAO.listarVotosPorCandidato();

        } catch (SQLException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("listaVotosPorCandidato", listaVotosPorCandidato);
        request.setAttribute("listaVotosPorPartido", listaVotosPorPartido);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/relatorios.jsp");
        dispatcher.forward(request, response);

    }
}
