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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        try {

            addUserToDB(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Add user in database.
     */
    private void addUserToDB(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {

        req.setCharacterEncoding("UTF8");

        final DBJoint db = (DBJoint) getServletContext().getAttribute("db");

        db.getDBScriptExecutor().addUser(
                new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                ));

        req.setAttribute("serverAnswer", "Пользователь успешно добавлен");
        req.getRequestDispatcher("/WEB-INF/views/answer.jsp").forward(req, resp);
    }
}
