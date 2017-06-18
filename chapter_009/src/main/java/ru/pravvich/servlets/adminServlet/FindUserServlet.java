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

import static ru.pravvich.servlets.Messages.FIND_ERROR;
import static ru.pravvich.servlets.Paths.FIND;
import static ru.pravvich.servlets.Paths.USER;

/**
 * Get user from database.
 */
public class FindUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        try {

            findUserById(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find user in database by id.
     */
    private void findUserById(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws ServletException, IOException, SQLException {

        final int id = Integer.parseInt(req.getParameter("id"));

        final User result = getScriptExecutor().getUserById(id);

        if (result.getId() == 0) {

            req.setAttribute("fail", FIND_ERROR.get());

            req.getRequestDispatcher(FIND.get())
                    .forward(req, resp);
        }

        req.setAttribute("user", result);
        req.getRequestDispatcher(USER.get()).forward(req, resp);
    }

    /**
     * Get ScriptExecutor for work with database.
     */
    private ScriptExecutor getScriptExecutor() throws SQLException {
        final DBJoint db = (DBJoint) getServletContext().getAttribute("db");
        return db.getDBScriptExecutor();
    }
}
