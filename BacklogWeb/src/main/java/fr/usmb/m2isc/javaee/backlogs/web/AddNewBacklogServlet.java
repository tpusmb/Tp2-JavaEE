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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/AddNewBacklogServlet")
public class AddNewBacklogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private Operation ejb;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewBacklogServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String backlog_id = req.getParameter("backlog_id");

        req.setAttribute("backlog_id", backlog_id);

        req.setAttribute("error", null);

        req.getRequestDispatcher("/add_backlog.jsp").forward(req, res);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        Long backlog_id = Long.parseLong(req.getParameter("backlog_id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int priority = Integer.parseInt(req.getParameter("priority"));
        int estimation = Integer.parseInt(req.getParameter("estimation"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Entry entry = new Entry(date,name,priority,estimation,description);

        Backlog backlog = ejb.addEntryToBacklog(entry,backlog_id);


        req.setAttribute("entries", backlog.getEntries());
        req.setAttribute("backlog_id", backlog_id);


        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
