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
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static ru.pravvich.servlets.Paths.ALL_USERS;

public class GetAllUsersServletTest {

    @Test
    public void whenGetAllUsers()
            throws SQLException, ServletException, IOException {

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(ALL_USERS.get()))
                .thenReturn(dispatcher);


        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);
        final ArrayList<User> users = new ArrayList<>();
        when(executor.getAllUsers()).thenReturn(users);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);

        final GetAllUsersServlet servlet = new GetAllUsersServlet();

        servlet.doPost(request, response);

        verify(request).getRequestDispatcher(ALL_USERS.get());
        verify(request).setAttribute("allUsers", users);
    }
}