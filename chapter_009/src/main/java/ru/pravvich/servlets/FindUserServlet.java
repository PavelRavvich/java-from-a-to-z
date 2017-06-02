package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.DBJointHandler;
import ru.pravvich.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Get user from database.
 */
public class FindUserServlet extends HttpServlet {

    private DBJoint db;

    public FindUserServlet() {
        super();
        db = new DBJointHandler("database_scripts", "authentication_database");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        try {

            getUser(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        final String id = req.getParameter("id");

        //final User result = db.getDBExecutor().getUser(Integer.parseInt(id));

        User result = new User(1, "test", "test", "test", new Timestamp(System.currentTimeMillis()));

        if (result.getId() == 0) {
            req.setAttribute("fail", "Такого пользователя не существует");
            req.getRequestDispatcher("find.jsp").forward(req, resp);
        }

        req.setAttribute("user", result);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
