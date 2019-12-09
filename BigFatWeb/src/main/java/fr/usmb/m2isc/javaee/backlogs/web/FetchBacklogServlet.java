package fr.usmb.m2isc.javaee.backlogs.web;


import fr.usmb.m2isc.javaee.backlogs.ejb.Operation;
import fr.usmb.m2isc.javaee.backlogs.jpa.Agence;
import fr.usmb.m2isc.javaee.backlogs.jpa.Backlog;
import fr.usmb.m2isc.javaee.backlogs.jpa.Entry;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet utilisee pour afficher un compte.
 */
@WebServlet("/FetchBacklogServlet")
public class FetchBacklogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private Operation ejb;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBacklogServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String agency_id = request.getParameter("agency_id");

        Agence a = ejb.getAgence(agency_id);
        Backlog b  = a.getBacklog();
        List<Entry> entries = b.getEntries();
        request.setAttribute("entries", entries);
        request.setAttribute("backlog_id", b.getId());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
