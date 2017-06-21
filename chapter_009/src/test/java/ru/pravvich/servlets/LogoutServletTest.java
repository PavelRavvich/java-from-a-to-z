package ru.pravvich.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoutServletTest {
    @Test
    public void logout() throws ServletException, IOException {

        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        final LogoutServlet servlet = new LogoutServlet();
        servlet.doPost(req, resp);

        verify(req).getContextPath();
        verify(resp).sendRedirect(req.getContextPath());
    }
}