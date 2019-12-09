package fr.usmb.m2isc.javaee.backlogs.web;

import fr.usmb.m2isc.javaee.backlogs.jpa.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpsession = req.getSession();
        User u = (User) httpsession.getAttribute("currentUser");
        httpsession.setAttribute("currentUser",null);
        String message = "Salut bro " + u.getUsername() + " !";

        req.setAttribute("global_notification_success",true);
        req.setAttribute("message",message);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
