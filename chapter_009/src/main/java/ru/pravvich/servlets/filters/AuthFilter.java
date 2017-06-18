package ru.pravvich.servlets.filters;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;
import ru.pravvich.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static java.util.Objects.nonNull;
import static ru.pravvich.servlets.Messages.FIND_ERROR;
import static ru.pravvich.servlets.Paths.*;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    private final static String password = "password";
    private final static String access   =   "access";
    private final static String admin    =    "admin";
    private final static String login    =    "login";
    private final static String user     =     "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final ScriptExecutor dBExecutor = getDBExecutor(req);


        final HttpSession session = req.getSession(false);

        if (nonNull(session) && userExistIn(session)) {

            final String success = getSessionAccess(session);

            moveToMenu(req, res, success);

            filterChain.doFilter(request, response);

        } else if (userExistInDB(req, dBExecutor)) {

            final String access = getAccess(req, dBExecutor);

            final int id = getId(req, dBExecutor);

            putSessionAttributes(req, access, id);

            moveToMenu(req, res, access);

        } else {

            moveToLoginPage(req, res);
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     * Else move to ErrorPage.
     *
     * @param access level.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String access)

            throws ServletException, IOException {

        if (access != null && access.equals(admin)) {

            req.getRequestDispatcher(ADMIN_MENU.get()).forward(req, res);

        } else if (access != null && access.equals(user)) {

            req.getRequestDispatcher(USER_MENU.get()).forward(req, res);

        } else {

            req.getRequestDispatcher(ERROR_ACCESS.get()).forward(req, res);
        }
    }

    /**
     * Put attributes in session.
     * Put user's login and access level and id into session.
     *
     * @param access for put.
     * @param id for put.
     */
    private void putSessionAttributes(final HttpServletRequest req,
                                      final String access,
                                      final int id)

            throws ServletException, IOException {

        final HttpSession session = req.getSession();

        synchronized (session) {
            session.setAttribute(login, req.getParameter(login));
            session.setAttribute("access", access);
            session.setAttribute("id", id);
        }
    }

    /**
     * Get access from current session.
     */
    private String getSessionAccess(final HttpSession session) {
        synchronized (session) {
            return (String) session.getAttribute(access);
        }
    }

    /**
     * Recognition user.
     *
     * @param session currant session.
     * @return true if session contain name and user recognition, else false.
     */
    private boolean userExistIn(final HttpSession session)
            throws ServletException, IOException {

        synchronized (session) {
            return (nonNull(session.getAttribute(login)));
        }
    }

    /**
     * Move to login page if pare login & password not exist in database.
     */
    private void moveToLoginPage(final HttpServletRequest req,
                                 final HttpServletResponse res)

            throws ServletException, IOException {


        if (req.getParameter(login) != null) {
            req.setAttribute("warning",FIND_ERROR.get());
        }

        req.getRequestDispatcher(LOGIN.get()).forward(req, res);
    }



    /**
     * Check in database exist user or not.
     *
     * @return true if user exist in DB, else false.
     */
    private boolean userExistInDB(final HttpServletRequest request,
                                  final ScriptExecutor executor) {

        final String log = request.getParameter(login);
        final String pas = request.getParameter(password);

        return executor.userIsExist(log, pas);
    }

    /**
     * Get access level from database by pair login and password.
     *
     * @return access level.
     */
    private String getAccess(final HttpServletRequest request,
                             final ScriptExecutor executor) {

        final String log = request.getParameter(login);
        final String pas = request.getParameter(password);

        return executor.getAccess(log, pas);

    }

    /**
     * For work with database.
     *
     * @return database ScriptExecutor.
     */
    private ScriptExecutor getDBExecutor(final ServletRequest req) {

        final DBJoint db = (DBJoint) req.getServletContext().getAttribute("db");

        try {

            return db.getDBScriptExecutor();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get id for user with pair login password from database.
     */
    private int getId(final HttpServletRequest req,
                      final ScriptExecutor executor) {


        final String login = req.getParameter(AuthFilter.login);
        final String password = req.getParameter(AuthFilter.password);

        final User user = executor.getUserByLoginPassword(login, password);

        return user.getId();
    }

    @Override
    public void destroy() {
    }
}
