package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Delete user from database by id.
 */
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            deleteUserById(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteUserById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {

        final DBJoint db = (DBJoint) getServletContext().getAttribute("db");

        final int id = Integer.parseInt(req.getParameter("id"));

        String answer;

        if (id <= 0) {
            answer = "Пользователя с таким id не может существовать";
        } else {
            answer = String.format("%s %s %s", "Пользователья с id =", id, "точно больше не существует.");
            //db.getDBScriptExecutor().deleteUser(new User(id));
        }


        req.setAttribute("serverAnswer", answer);

        req.getRequestDispatcher("/WEB-INF/views/answer.jsp").forward(req, resp);
    }
}
