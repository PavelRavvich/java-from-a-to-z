package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.DBJointFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Edition user.
 */
public class EditUserServlet extends HttpServlet {

    private DBJoint db;

    public EditUserServlet() {
        super();
        db = new DBJointFactory("database_scripts", "authentication_database");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        try {

            editUserInDB(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editUserInDB(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final String login = req.getParameter("login");
        final String email = req.getParameter("email");

        //db.getDBExecutor().updateUser(new User(Integer.parseInt(id), name, login, email));

        req.setAttribute("warning", "такого пользователя не существует");
        req.getRequestDispatcher("edition.jsp").forward(req, resp);
    }
}
