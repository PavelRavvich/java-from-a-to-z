package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Addition user to database from html form.
 */
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        req.getRequestDispatcher("/WEB-INF/views/addition.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (moveToAddition(req, resp)) return;

        try {

            addUserToDB(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Move to /WEB-INF/views/addition.jsp.
     *
     * @return true if request should get addition's page. Else false.
     */
    private boolean moveToAddition(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String[] acts = req.getParameterValues("act");

        if (acts == null) return false;

        for (final String act : acts) {

            if (act != null) {

                req.getRequestDispatcher("/WEB-INF/views/addition.jsp").forward(req, resp);

                return true;
            }
        }

        return false;
    }

    /**
     * Add user in database.
     */
    private void addUserToDB(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {

        req.setCharacterEncoding("UTF8");

        DBJoint db = (DBJoint) getServletContext().getAttribute("db");

        db.getDBExecutor().addUser(
                new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")));

        req.setAttribute("serverAnswer", "Пользователь успешно добавлен");
        req.getRequestDispatcher("/WEB-INF/views/answer.jsp").forward(req, resp);
    }
}
