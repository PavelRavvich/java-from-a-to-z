package ru.pravvich.servlets.filters;

import org.junit.Test;
import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;
import ru.pravvich.user.User;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static ru.pravvich.servlets.Messages.FIND_ERROR;
import static ru.pravvich.servlets.Paths.*;

public class AuthFilterTest {
    @Test
    public void whenSessionIsExist()
            throws SQLException, IOException, ServletException {

        //Http mocking.
        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse resp = mock(HttpServletResponse.class);
        final FilterChain chain = mock(FilterChain.class);

        //Database mocking.
        final ServletContext context = mock(ServletContext.class);
        final DBJoint joint = mock(DBJoint.class);
        final ScriptExecutor executor = mock(ScriptExecutor.class);
        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        //Session mocking.
        final HttpSession session = mock(HttpSession.class);
        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("login");
        when(session.getAttribute("access")).thenReturn("admin");

        //Dispatcher mocking.
        when(req.getRequestDispatcher(ADMIN_MENU.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //Filter action.
        final AuthFilter authFilter = new AuthFilter();
        authFilter.doFilter(req, resp, chain);

        //Test.
        verify(req).getRequestDispatcher(ADMIN_MENU.get());
    }

    @Test
    public void whenUserIsExistInDatabaseButSessionIsNotExistAndAccessAdmin()
            throws SQLException, IOException, ServletException {

        //Http mocking.
        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse resp = mock(HttpServletResponse.class);
        final FilterChain chain = mock(FilterChain.class);


        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");


        //Database mocking.
        final ServletContext context = mock(ServletContext.class);
        final DBJoint joint = mock(DBJoint.class);
        final ScriptExecutor executor = mock(ScriptExecutor.class);
        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);
        when(joint.getDBScriptExecutor()).thenReturn(executor);
        when(executor.userIsExist("login", "password")).thenReturn(true);
        final User user = new User();
        user.setId(1);
        when(executor.getUserByLoginPassword("login", "password"))
                .thenReturn(user);
        when(executor.getAccess("login", "password")).thenReturn("admin");


        //Session mocking.
        final HttpSession session = mock(HttpSession.class);
        when(req.getSession(false)).thenReturn(null);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("login");
        when(session.getAttribute("access")).thenReturn("admin");


        //Dispatcher mocking.
        when(req.getRequestDispatcher(ADMIN_MENU.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //Filter action.
        final AuthFilter authFilter = new AuthFilter();
        authFilter.doFilter(req, resp, chain);

        //Test.
        verify(req).getRequestDispatcher(ADMIN_MENU.get());
        verify(session).setAttribute("login", "login");
        verify(session).setAttribute("access", "admin");
        verify(session).setAttribute("id", 1);
    }

    @Test
    public void whenUserIsExistInDatabaseButSessionIsNotExistAndAccessUser()
            throws SQLException, IOException, ServletException {

        //Http mocking.
        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse resp = mock(HttpServletResponse.class);
        final FilterChain chain = mock(FilterChain.class);


        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");


        //Database mocking.
        final ServletContext context = mock(ServletContext.class);
        final DBJoint joint = mock(DBJoint.class);
        final ScriptExecutor executor = mock(ScriptExecutor.class);
        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);
        when(joint.getDBScriptExecutor()).thenReturn(executor);
        when(executor.userIsExist("login", "password")).thenReturn(true);
        final User user = new User();
        user.setId(1);
        when(executor.getUserByLoginPassword("login", "password"))
                .thenReturn(user);
        when(executor.getAccess("login", "password")).thenReturn("user");


        //Session mocking.
        final HttpSession session = mock(HttpSession.class);
        when(req.getSession(false)).thenReturn(null);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("login");
        when(session.getAttribute("access")).thenReturn("admin");


        //Dispatcher mocking.
        when(req.getRequestDispatcher(USER_MENU.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //Filter action.
        final AuthFilter authFilter = new AuthFilter();
        authFilter.doFilter(req, resp, chain);

        //Test.
        verify(req).getRequestDispatcher(USER_MENU.get());
        verify(session).setAttribute("login", "login");
        verify(session).setAttribute("access", "user");
        verify(session).setAttribute("id", 1);
    }

    @Test
    public void whenUserExistInDatabaseButAccessNotFound()
            throws SQLException, IOException, ServletException {

        //Http mocking.
        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse resp = mock(HttpServletResponse.class);
        final FilterChain chain = mock(FilterChain.class);


        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");


        //Database mocking.
        final ServletContext context = mock(ServletContext.class);
        final DBJoint joint = mock(DBJoint.class);
        final ScriptExecutor executor = mock(ScriptExecutor.class);
        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);
        when(joint.getDBScriptExecutor()).thenReturn(executor);
        when(executor.userIsExist("login", "password")).thenReturn(true);
        final User user = new User();
        user.setId(1);
        when(executor.getUserByLoginPassword("login", "password"))
                .thenReturn(user);
        when(executor.getAccess("login", "password")).thenReturn("");


        //Session mocking.
        final HttpSession session = mock(HttpSession.class);
        when(req.getSession(false)).thenReturn(null);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("login");
        when(session.getAttribute("access")).thenReturn("");


        //Dispatcher mocking.
        when(req.getRequestDispatcher(ERROR_ACCESS.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //Filter action.
        final AuthFilter authFilter = new AuthFilter();
        authFilter.doFilter(req, resp, chain);

        //Test.
        verify(req).getRequestDispatcher(ERROR_ACCESS.get());
        verify(session).setAttribute("login", "login");
        verify(session).setAttribute("access", "");
        verify(session).setAttribute("id", 1);
    }

    @Test
    public void whenUserNotExistInDatabase()
            throws SQLException, IOException, ServletException {

        //Http mocking.
        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse resp = mock(HttpServletResponse.class);
        final FilterChain chain = mock(FilterChain.class);


        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");


        //Database mocking.
        final ServletContext context = mock(ServletContext.class);
        final DBJoint joint = mock(DBJoint.class);
        final ScriptExecutor executor = mock(ScriptExecutor.class);
        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);
        when(joint.getDBScriptExecutor()).thenReturn(executor);
        when(executor.userIsExist("login", "password")).thenReturn(false);
        final User user = new User();
        user.setId(1);
        when(executor.getUserByLoginPassword("login", "password"))
                .thenReturn(user);
        when(executor.getAccess("login", "password")).thenReturn("");


        //Dispatcher mocking.
        when(req.getRequestDispatcher(LOGIN.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //Filter action.
        final AuthFilter authFilter = new AuthFilter();
        authFilter.doFilter(req, resp, chain);

        //Test.
        verify(req).getRequestDispatcher(LOGIN.get());
        verify(req).setAttribute("warning", FIND_ERROR.get());
    }
}