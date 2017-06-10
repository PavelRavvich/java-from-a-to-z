package ru.pravvich.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Get home page index.jsp and navigation by support action.
 */
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String select = moveSelect(req);

        if (select.equals("")) {
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }


        if (select.equals("all")) {
            getServletContext().getRequestDispatcher("/getall").forward(req,resp);
        } else {
            req.getRequestDispatcher(format("/WEB-INF/views/%s.jsp", select)).forward(req, resp);
        }

    }

    /**
     * Determines select user's action on homepage.
     *
     * @return value input which select, or empty string if select is empty.
     */
    private String moveSelect(final HttpServletRequest req) {

        final String[] acts = req.getParameterValues("act");

        if (acts == null) return "";

        for (final String act : acts) {

            if (act != null) {

                return act;
            }
        }

        return "";
    }
}
