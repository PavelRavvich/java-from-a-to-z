package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Get user from database.
 */
public class FindUserServlet extends HttpServlet {

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

        final DBJoint db = (DBJoint) getServletContext().getAttribute("db");

        //final User result = db.getDBScriptExecutor().getUser(Integer.parseInt(id));

//        if (result.getId() == 0) {
//            req.setAttribute("fail", "Такого пользователя не существует");
//            req.getRequestDispatcher("/WEB-INF/views/find.jsp").forward(req, resp);
//        }

        //req.setAttribute("user", result);
        req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
    }
}
