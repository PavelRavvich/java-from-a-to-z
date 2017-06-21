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

import static ru.pravvich.servlets.Messages.EDIT_SUCCESS;
import static ru.pravvich.servlets.Messages.ERROR_EDIT;
import static ru.pravvich.servlets.Paths.ANSWER;
import static ru.pravvich.servlets.Paths.EDITION;

/**
 * Edition user.
 */
public class EditUserServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        try {

            editUserInDB(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edition user in database.
     */
    private void editUserInDB(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {

        final int id = Integer.parseInt(req.getParameter("id"));

        final ScriptExecutor executor = getExecutor(req);

        final User user = getUser(req);

        final boolean success = executor.updateUserAndGet(id, user);

        final String answer = success ? EDIT_SUCCESS.get() : ERROR_EDIT.get();

        final String path = success ? ANSWER.get() : EDITION.get();

        final String attribute = success ? "serverAnswer" : "warning";

        req.setAttribute(attribute, answer);

        req.getRequestDispatcher(path)
                .forward(req, resp);
    }

    /**
     * Extracts User from request.
     */
    private User getUser(final HttpServletRequest req) {

        final String name = req.getParameter("name");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        final String role = req.getParameter("role");

        return new User(name, login, password, email, role);
    }

    /**
     * Get database ScriptExecutor from ServletContext.
     */
    private ScriptExecutor getExecutor(final HttpServletRequest req)
            throws SQLException {

        final DBJoint db = (DBJoint) req.getServletContext().getAttribute("db");

        return db.getDBScriptExecutor();
    }
}
