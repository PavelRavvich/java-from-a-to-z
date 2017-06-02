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
 * Addition user to database from html form.
 */
public class AddUserServlet extends HttpServlet {

    private DBJoint db;

    public AddUserServlet() {
        super();
        db = new DBJointFactory("database_scripts", "authentication_database");
    }

    public void setDb(DBJoint db) {
        this.db = db;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        try {

            addUserToDB(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addUserToDB(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {

        final String name = req.getParameter("name");
        final String login = req.getParameter("login");
        final String email = req.getParameter("email");

        //db.getDBExecutor().addUser(new User(name, login, email));

        req.setAttribute("serverAnswer", "Пользователь успешно добавлен");
        req.getRequestDispatcher("answer.jsp").forward(req, resp);
    }

}
