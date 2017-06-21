package ru.pravvich.servlets.adminServlet;

import org.junit.Test;
import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.DBJointHandler;
import ru.pravvich.jdbc.ScriptExecutor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static ru.pravvich.servlets.Messages.DELETE;
import static ru.pravvich.servlets.Messages.FIND_ERROR;
import static ru.pravvich.servlets.Paths.ANSWER;

public class DeleteUserServletTest {

    @Test
    public void whenUserIdExistMockDatabase()
            throws SQLException, ServletException, IOException {

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");


        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(ANSWER.get()))
                .thenReturn(dispatcher);

        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);

        when(executor.userIsExist(1)).thenReturn(true);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);


        final DeleteUserServlet servlet = new DeleteUserServlet();
        servlet.doPost(request, response);

        verify(request).setAttribute("serverAnswer", DELETE.get());
        verify(request).getRequestDispatcher(ANSWER.get());
    }

    @Test
    public void whenUserIdNotExistInMockDatabase()
            throws SQLException, ServletException, IOException {

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");


        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(ANSWER.get()))
                .thenReturn(dispatcher);

        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);

        when(executor.userIsExist(1)).thenReturn(false);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);


        final DeleteUserServlet servlet = new DeleteUserServlet();
        servlet.doPost(request, response);

        verify(request).setAttribute("serverAnswer", FIND_ERROR.get());
        verify(request).getRequestDispatcher(ANSWER.get());
    }
}