package ru.pravvich.servlets.adminServlet;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ru.pravvich.servlets.Messages.DELETE;
import static ru.pravvich.servlets.Messages.FIND_ERROR;
import static ru.pravvich.servlets.Paths.ANSWER;

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

    /**
     * Delete user from database.
     */
    private void deleteUserById(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException, SQLException {

        final int id = Integer.parseInt(req.getParameter("id"));

        final ScriptExecutor executor = getExecutor(req);



        if (executor.userIsExist(id)) {

            executor.deleteUserById(id);

            req.setAttribute("serverAnswer", DELETE.get());

        } else {

            req.setAttribute("serverAnswer", FIND_ERROR.get());
        }


        req.getRequestDispatcher(ANSWER.get()).forward(req, resp);
    }

    private ScriptExecutor getExecutor(final HttpServletRequest req)
            throws SQLException {

        final DBJoint db = (DBJoint) req.getServletContext().getAttribute("db");
        return db.getDBScriptExecutor();
    }
}
