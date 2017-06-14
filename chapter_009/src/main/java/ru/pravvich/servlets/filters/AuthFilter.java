package ru.pravvich.servlets.filters;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static java.util.Objects.nonNull;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;


        final HttpSession session = req.getSession(false);


//        if (nonNull(session) && sessionContainUser(session)) {
//
//            req.getRequestDispatcher("/WEB-INF/views/AdminMenu.jsp")
//                    .forward(req, res);
//
//            filterChain.doFilter(request, response);
//
//        } else if (req.getParameter("name") == null) {
//
//            moveToLoginPage(req, res);
//
//        } else if (userExistInDB(req, getDBExecutor(req))) {
//
//            moveToMenu(req, res);
//
//            filterChain.doFilter(request, response);
//
//        } else {
//
//            moveToLoginPage(req, res, "Нет такого пользователя");
//
//        }
    }


    private void moveToLoginPage(final HttpServletRequest req,
                                 final HttpServletResponse res,
                                 final String ... massage)

            throws ServletException, IOException {

        if (massage.length == 1) {
            req.setAttribute("warning", "Нет такого пользователя");
        }

        req.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(req, res);
    }


    /**
     * Move user to menu.
     */
    private void moveToMenu(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        setSessionAttribute(req.getSession(), req.getParameter("name"));

        req.getRequestDispatcher("/WEB-INF/views/AdminMenu.jsp")
                .forward(req, res);

    }

    /**
     * Save name/key in session.
     *
     * @param session current session.
     * @param name key which save in session for recognition of user.
     */
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
//    private boolean userExistInDB(HttpServletRequest request, ScriptExecutor scriptExecutor) {
//
//        final String name = request.getParameter("name");
//        final String email = request.getParameter("email");
//
//        return scriptExecutor.userIsExist(new User(name, "stub_for_login", email));
//    }

    /**
     * For work with database.
     *
     * @return database ScriptExecutor.
     */
    private ScriptExecutor getDBExecutor(ServletRequest req) {

        final DBJoint db = (DBJoint) req.getServletContext().getAttribute("db");

        try {

            return db.getDBScriptExecutor();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Recognition user.
     *
     * @param session currant session.
     * @return true if session contain name and user recognition, else false.
     */
    private boolean sessionContainUser(final HttpSession session)
            throws ServletException, IOException {

        synchronized (session) {
            return (nonNull(session.getAttribute("name")));
        }
    }

    @Override
    public void destroy() {
    }
}
