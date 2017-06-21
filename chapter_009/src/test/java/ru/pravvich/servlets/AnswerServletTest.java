package ru.pravvich.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static ru.pravvich.servlets.Paths.*;

public class AnswerServletTest {
    @Test
    public void whenSuccessIsAdmin() throws ServletException, IOException {

        final HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getRequestDispatcher(ADMIN_MENU.get()))
                .thenReturn(mock(RequestDispatcher.class));

        final HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("access")).thenReturn("admin");
        when(req.getSession(false)).thenReturn(session);


        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final AnswerServlet servlet = new AnswerServlet();

        servlet.doPost(req, resp);


        verify(req).getRequestDispatcher(ADMIN_MENU.get());
    }

    @Test
    public void whenSuccessIsUser() throws ServletException, IOException {

        final HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getRequestDispatcher(USER_MENU.get()))
                .thenReturn(mock(RequestDispatcher.class));

        final HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("access")).thenReturn("user");
        when(req.getSession(false)).thenReturn(session);


        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final AnswerServlet servlet = new AnswerServlet();

        servlet.doPost(req, resp);


        verify(req).getRequestDispatcher(USER_MENU.get());
    }

    @Test
    public void whenSessionIsNull() throws ServletException, IOException {

        final HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getRequestDispatcher(ERROR_ACCESS.get()))
                .thenReturn(mock(RequestDispatcher.class));
        when(req.getSession(false)).thenReturn(null);

        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final AnswerServlet servlet = new AnswerServlet();

        servlet.doPost(req, resp);


        verify(req).getRequestDispatcher(ERROR_ACCESS.get());
    }
}