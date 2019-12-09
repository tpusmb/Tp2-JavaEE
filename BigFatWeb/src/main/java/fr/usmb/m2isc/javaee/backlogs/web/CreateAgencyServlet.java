package fr.usmb.m2isc.javaee.backlogs.web;

import fr.usmb.m2isc.javaee.backlogs.ejb.Operation;
import fr.usmb.m2isc.javaee.backlogs.jpa.Agence;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet utilisee pour afficher un compte.
 */
@WebServlet("/CreateAgencyServlet")
public class CreateAgencyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private Operation ejb;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAgencyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        ejb.createAgency(name);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("listAgence",ejb.getAllAgence());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
