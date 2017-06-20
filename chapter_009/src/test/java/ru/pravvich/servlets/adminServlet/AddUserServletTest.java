package ru.pravvich.servlets.adminServlet;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.DBJointHandler;
import ru.pravvich.jdbc.ScriptExecutor;
import ru.pravvich.user.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.pravvich.servlets.Messages.EDIT_SUCCESS;

public class AddUserServletTest {
    @Test
    public void whenUserAddThenDatabaseThenServerAnswerIsEDIT_SUCCESS()
            throws ServletException, IOException, SQLException {

        final AddUserServlet servlet = new AddUserServlet();
        User user = new User("name", "login", "password", "email", "admin");

        //mock http.
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getAttribute("name")).thenReturn(user.getName());
        when(request.getAttribute("login")).thenReturn(user.getLogin());
        when(request.getAttribute("password")).thenReturn(user.getPassword());
        when(request.getAttribute("email")).thenReturn(user.getEmail());
        when(request.getAttribute("role")).thenReturn(user.getSuccessLevel());


        //mock database.
        ScriptExecutor executor = mock(ScriptExecutor.class);

        when(executor.addUserAndGetSuccess(user)).thenReturn(true);

        DBJoint joint = mock(DBJointHandler.class);
        when(joint.getDBScriptExecutor()).thenReturn(executor);

        ServletContext context = mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("db")).thenReturn(joint);

        servlet.doPost(request, response);

        final String result = (String) request.getAttribute("serverAnswer");
        Assert.assertThat(result, is(EDIT_SUCCESS.get()));
    }
}