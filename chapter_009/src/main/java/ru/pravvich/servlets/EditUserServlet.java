package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        db = (DBJoint) getServletContext().getAttribute("db");

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

//        final User user = db.getDBScriptExecutor().getUser(Integer.parseInt(id));
//
//        if (user.getId() == 0) {
//
//            req.setAttribute("warning", "такого пользователя не существует");
//            req.getRequestDispatcher("/WEB-INF/views/edition.jsp").forward(req, resp);
//
//        } else {
//
//            db.getDBScriptExecutor().updateUser(new User(Integer.parseInt(id), name, login, email));
//            req.setAttribute("user", new User(user.getId(),name,login,email,user.getCreateAccount()));
//            req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
//        }
    }
}
