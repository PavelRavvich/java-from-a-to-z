package ru.pravvich.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.pravvich.servlets.Paths.*;

/**
 * Answer about action's result.
 */
public class AnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");


        final String access = getAccess(req);

        if (access.equals("admin")) {

            req.getRequestDispatcher(ADMIN_MENU.get()).forward(req, resp);
        }


        if (access.equals("user")) {

            req.getRequestDispatcher(USER_MENU.get()).forward(req, resp);
        }


        if (access.equals("")) {

            req.getRequestDispatcher(ERROR_ACCESS.get()).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doGet(req, resp);
    }

    /**
     * Get access level for current session.
     */
    private String getAccess(final HttpServletRequest req) {

        final HttpSession session = req.getSession(false);

        if (session == null) return "";

        synchronized (session) {
            return (String) session.getAttribute("access");
        }
    }
}
