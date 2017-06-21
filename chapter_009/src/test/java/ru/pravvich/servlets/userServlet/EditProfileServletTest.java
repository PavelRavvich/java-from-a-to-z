package ru.pravvich.servlets.userServlet;

import org.junit.Test;
import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.ScriptExecutor;
import ru.pravvich.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static ru.pravvich.servlets.Messages.EDIT_SUCCESS;
import static ru.pravvich.servlets.Messages.ERR_UNIQUE_L_P;
import static ru.pravvich.servlets.Paths.ANSWER;
import static ru.pravvich.servlets.Paths.EDIT_PROFILE;

public class EditProfileServletTest {
    @Test
    public void whenUpdateSuccessful()
            throws SQLException, ServletException, IOException {

        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final HttpServletRequest req = mock(HttpServletRequest.class);

        final HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("id")).thenReturn(1);

        final ScriptExecutor executor = mock(ScriptExecutor.class);
        when(executor.updateUserAndGet(eq(1), any(User.class)))
                .thenReturn(true);

        final DBJoint joint = mock(DBJoint.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        final ServletContext context = mock(ServletContext.class);
        when(context.getAttribute("db")).thenReturn(joint);


        when(req.getSession(false)).thenReturn(session);
        when(req.getServletContext()).thenReturn(context);
        when(req.getRequestDispatcher(ANSWER.get()))
                .thenReturn(mock(RequestDispatcher.class));


        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");
        when(req.getParameter("email")).thenReturn("email");

        final EditProfileServlet servlet = new EditProfileServlet();
        servlet.doPost(req, resp);

        verify(req).getRequestDispatcher(ANSWER.get());
        verify(req).setAttribute("serverAnswer", EDIT_SUCCESS.get());
    }


    @Test
    public void whenUpdateFail()
            throws SQLException, ServletException, IOException {

        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final HttpServletRequest req = mock(HttpServletRequest.class);

        final HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("id")).thenReturn(1);

        final ScriptExecutor executor = mock(ScriptExecutor.class);
        when(executor.updateUserAndGet(eq(1), any(User.class)))
                .thenReturn(false);

        final DBJoint joint = mock(DBJoint.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        final ServletContext context = mock(ServletContext.class);
        when(context.getAttribute("db")).thenReturn(joint);


        when(req.getSession(false)).thenReturn(session);
        when(req.getServletContext()).thenReturn(context);
        when(req.getRequestDispatcher(EDIT_PROFILE.get()))
                .thenReturn(mock(RequestDispatcher.class));


        final EditProfileServlet servlet = new EditProfileServlet();
        servlet.doPost(req, resp);

        verify(req).getRequestDispatcher(EDIT_PROFILE.get());
        verify(req).setAttribute("error", ERR_UNIQUE_L_P.get());
    }
}