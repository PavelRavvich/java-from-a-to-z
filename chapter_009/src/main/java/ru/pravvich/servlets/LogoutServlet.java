package ru.pravvich.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout.
 * Delete session.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)

            throws ServletException, IOException {


        final HttpSession session = req.getSession();

        synchronized (session) {
            session.removeAttribute("login");
            session.removeAttribute("access");
        }

         resp.sendRedirect(req.getContextPath());
    }

}
