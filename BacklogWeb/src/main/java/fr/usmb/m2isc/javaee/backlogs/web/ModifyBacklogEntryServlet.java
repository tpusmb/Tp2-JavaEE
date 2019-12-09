package fr.usmb.m2isc.javaee.backlogs.web;

import fr.usmb.m2isc.javaee.backlogs.ejb.Operation;
import fr.usmb.m2isc.javaee.backlogs.jpa.Backlog;
import fr.usmb.m2isc.javaee.backlogs.jpa.Entry;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet utilisee pour afficher un compte.
 */
@WebServlet("/ModifyBacklogEntryServlet")
public class ModifyBacklogEntryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private Operation ejb;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyBacklogEntryServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long entry_id = Long.parseLong(request.getParameter("entry_id"));

        Entry e = ejb.getEntry(entry_id);
        request.setAttribute("entry", e);

        request.getRequestDispatcher("/modify_backlog.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long entry_id = Long.parseLong(request.getParameter("entry_id"));
        String name = request.getParameter("name");
        int estimation = Integer.parseInt(request.getParameter("estimation"));
        int priority = Integer.parseInt(request.getParameter("priority"));
        String description = request.getParameter("description");

        ejb.updateEntry(entry_id, name, description, priority, estimation);


        String message = "Entry n°"+ entry_id +" has been updated.";

        request.setAttribute("notification_success",true);
        request.setAttribute("message",message);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
