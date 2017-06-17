package ru.pravvich.servlets.adminServlet;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;
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
    /**
     * Answers text for user about addition in database.
     */
    private static final String SUCCESS = "Пользователь успешно добавлен";
    private static final String FAIL = "Такой пользователь уже существует";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        try {

            final boolean success = addUserInDatabase(req);

            if (success) req.setAttribute("serverAnswer", SUCCESS);
            else req.setAttribute("serverAnswer", FAIL);

            req.getRequestDispatcher("/WEB-INF/views/answer.jsp")
                    .forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Addition user in database.
     *
     * @return true if addition success, else false.
     */
    private boolean addUserInDatabase(final HttpServletRequest req)
            throws SQLException {

        final User user = getUserFromRequest(req);
        return getDatabaseExecutor().addUserAndGetSuccess(user);
    }

    /**
     * Extracts user's data from HttpServletRequest.
     *
     * @return user from request.
     */
    private User getUserFromRequest(final HttpServletRequest req) {
        return new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("email"),
                req.getParameter("role")
        );
    }

    /**
     * Get executor database requests.
     */
    private ScriptExecutor getDatabaseExecutor() throws SQLException {
        final DBJoint db = (DBJoint) getServletContext().getAttribute("db");
        return db.getDBScriptExecutor();
    }
}
