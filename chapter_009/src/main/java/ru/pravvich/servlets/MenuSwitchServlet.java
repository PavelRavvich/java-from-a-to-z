package ru.pravvich.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pavel on 06.06.17.
 */
public class MenuSwitchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String key = req.getParameter("key");

        if (key.equals("add")) {
            req.getRequestDispatcher("/WEB-INF/views/addition.jsp").forward(req, resp);
        }
    }
}
