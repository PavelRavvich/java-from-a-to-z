package ru.pravvich.servlets.adminServlet;

import org.junit.Test;
import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.DBJointHandler;
import ru.pravvich.jdbc.ScriptExecutor;
import ru.pravvich.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static ru.pravvich.servlets.Messages.EDIT_SUCCESS;
import static ru.pravvich.servlets.Messages.ERROR_EDIT;
import static ru.pravvich.servlets.Paths.ANSWER;
import static ru.pravvich.servlets.Paths.EDITION;

public class EditUserServletTest {

    @Test
    public void whenUserSuccessfullyUpdated()
            throws SQLException, ServletException, IOException {

        User user = new User("name", "login", "password", "email", "admin");

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn(user.getName());
        when(request.getParameter("login")).thenReturn(user.getLogin());
        when(request.getParameter("password")).thenReturn(user.getPassword());
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("role")).thenReturn(user.getSuccessLevel());


        when(request.getRequestDispatcher(ANSWER.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);

        when(executor.updateUserAndGet(1, user)).thenReturn(true);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);


        final EditUserServlet servlet = new EditUserServlet();
        servlet.doPost(request, response);


        verify(request).getRequestDispatcher(ANSWER.get());
        verify(request).setAttribute("serverAnswer", EDIT_SUCCESS.get());
    }

    @Test
    public void whenEditionFail()
            throws ServletException, IOException, SQLException {

        User user = new User("name", "login", "password", "email", "admin");

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn(user.getName());
        when(request.getParameter("login")).thenReturn(user.getLogin());
        when(request.getParameter("password")).thenReturn(user.getPassword());
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("role")).thenReturn(user.getSuccessLevel());


        when(request.getRequestDispatcher(EDITION.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);

        when(executor.updateUserAndGet(1, user)).thenReturn(false);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);


        final EditUserServlet servlet = new EditUserServlet();
        servlet.doPost(request, response);


        verify(request).getRequestDispatcher(EDITION.get());
        verify(request).setAttribute("warning", ERROR_EDIT.get());
    }
}