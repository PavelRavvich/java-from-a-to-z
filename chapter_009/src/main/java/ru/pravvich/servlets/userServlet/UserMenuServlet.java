package ru.pravvich.servlets.userServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.pravvich.servlets.Paths.EDIT_PROFILE;

/**
 * User menu viewer.
 */
public class UserMenuServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf8");

        if (req.getParameter("act").equals("edition")) {

            req.getRequestDispatcher(EDIT_PROFILE.get()).forward(req, resp);

        }
    }
}
