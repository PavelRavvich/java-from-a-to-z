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
 * Delete user from database by id..
 */
public class DeleteUserServlet extends HttpServlet {

    private DBJoint db;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        db = (DBJoint) getServletContext().getAttribute("db");

        try {

            delUserFromDB(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delUserFromDB(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        final int id = Integer.parseInt(req.getParameter("id"));

        String answer;

        if (id <= 0) {
            answer = "Пользователя с таким id не может существовать";
        } else {
            answer = String.format("%s %s %s", "Пользователья с id =", id, "точно больше не существует.");
            db.getDBExecutor().deleteUser(new User(id));
        }

        req.setAttribute("serverAnswer", answer);
        req.getRequestDispatcher("answer.jsp").forward(req, resp);


    }
}
