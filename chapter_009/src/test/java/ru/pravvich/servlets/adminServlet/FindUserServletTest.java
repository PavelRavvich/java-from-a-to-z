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
import static ru.pravvich.servlets.Messages.FIND_ERROR;
import static ru.pravvich.servlets.Paths.FIND;
import static ru.pravvich.servlets.Paths.USER;

public class FindUserServletTest {

    @Test
    public void whenFindingUserExist()
            throws SQLException, ServletException, IOException {

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");

        when(request.getRequestDispatcher(USER.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);
        User user = new User("name", "login", "password", "email", "admin");
        user.setId(1);
        when(executor.getUserById(1)).thenReturn(user);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);


        final FindUserServlet servlet = new FindUserServlet();
        servlet.doPost(request, response);


        verify(request).setAttribute("user", user);
        verify(request).getRequestDispatcher(USER.get());
    }

    @Test
    public void whenUserNotExist()
            throws SQLException, ServletException, IOException {

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("0");

        when(request.getRequestDispatcher(FIND.get()))
                .thenReturn(mock(RequestDispatcher.class));

        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);
        User user = new User();
        when(executor.getUserById(0)).thenReturn(user);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);


        final FindUserServlet servlet = new FindUserServlet();
        servlet.doPost(request, response);


        verify(request).setAttribute("fail", FIND_ERROR.get());
        verify(request).getRequestDispatcher(FIND.get());

    }
}