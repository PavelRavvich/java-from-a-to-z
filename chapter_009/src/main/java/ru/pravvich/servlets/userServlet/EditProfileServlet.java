package ru.pravvich.servlets.userServlet;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;
import ru.pravvich.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static ru.pravvich.servlets.Messages.EDIT_SUCCESS;
import static ru.pravvich.servlets.Messages.ERR_UNIQUE_L_P;
import static ru.pravvich.servlets.Paths.ANSWER;
import static ru.pravvich.servlets.Paths.EDIT_PROFILE;

/**
 * Edit user himself.
 */
public class EditProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("utf8");


        try {

            updateProfile(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void updateProfile(final HttpServletRequest req,
                               final HttpServletResponse resp)

            throws SQLException, ServletException, IOException {


        final ScriptExecutor executor = getScriptExecutor(req);


        final User forUpdate = getNewState(req);
        forUpdate.setSuccessLevel("user");

        final int id = getUserIdFromSession(req);
        final boolean success = executor.updateUserAndGet(id, forUpdate);

        if (success) {

            req.setAttribute("serverAnswer", EDIT_SUCCESS.get());

            req.getRequestDispatcher(ANSWER.get()).forward(req, resp);

        } else {

            req.setAttribute("error", ERR_UNIQUE_L_P.get());

            req.getRequestDispatcher(EDIT_PROFILE.get()).forward(req, resp);
        }
    }


    private User getNewState(final HttpServletRequest request) {

        final String name = request.getParameter("name");
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final String email = request.getParameter("email");

        if (nonNull(name, login, password, email)) {

            return new User(name, login, password, email);
        } else {

            final String flag = "-1";
            return new User(flag, flag, flag, flag);
        }
    }

    private ScriptExecutor getScriptExecutor(final HttpServletRequest req)
            throws SQLException {

        final DBJoint db = (DBJoint) req.getServletContext().getAttribute("db");

        return db.getDBScriptExecutor();
    }

    private boolean nonNull(final String ... params) {
        for (final String p : params) {
            if (p == null) return false;
        }

        return true;
    }

    private int getUserIdFromSession(final HttpServletRequest request) {

        final HttpSession session = request.getSession(false);

        synchronized (session) {
            return (int) session.getAttribute("id");
        }
    }
}
