package ru.pravvich.servlets;

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

/**
 * Login.
 */
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");

        if (sessionIsExist(request)) {

            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    private boolean sessionIsExist(HttpServletRequest request)
            throws ServletException, IOException {


        final HttpSession session = request.getSession(false);

        if (session == null) return false;

        synchronized (session) {

            return session.getAttribute("name") != null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            if (userIsExist(request, getDBExecutor())) {

                setSessionAttribute(request.getSession(), request.getParameter("name"));

                request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

            } else {

                request.setAttribute("warning", "Такого пользователя не существует");

                doGet(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void setSessionAttribute(final HttpSession session, final String name) {
        synchronized (session) {
            session.setAttribute("name", name);
        }
    }

    /**
     * Check in database exist user or not.
     *
     * @return true if user exist in DB, else false.
     */
    private boolean userIsExist(HttpServletRequest request, ScriptExecutor scriptExecutor) {

        final String name = request.getParameter("name");
        final String email = request.getParameter("email");

        return scriptExecutor.userIsExist(new User(name, "stub_for_login", email));
    }

    /**
     * For work with database.
     *
     * @return database ScriptExecutor.
     */
    private ScriptExecutor getDBExecutor() throws SQLException {
        final DBJoint db = (DBJoint) getServletContext().getAttribute("db");
        return db.getDBScriptExecutor();
    }
}
