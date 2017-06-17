package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ru.pravvich.servlets.Paths.ALL_USERS;

/**
 * Controller for view all users.
 */
public class GetAllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");

        try {

            final ScriptExecutor dbExecutor = getDBExecutor();
            handledRequest(request, response, dbExecutor);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handledRequest(HttpServletRequest request,
                                HttpServletResponse response,
                                ScriptExecutor dbExecutor)

            throws ServletException, IOException {


        request.setAttribute("allUsers", dbExecutor.getAllUsers());
        request.getRequestDispatcher(ALL_USERS.get())
                .forward(request, response);

    }

    private ScriptExecutor getDBExecutor() throws SQLException {

        final DBJoint db = (DBJoint) getServletContext().getAttribute("db");

        return db.getDBScriptExecutor();

    }

}
