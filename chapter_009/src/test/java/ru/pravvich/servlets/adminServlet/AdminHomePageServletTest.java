package ru.pravvich.servlets.adminServlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static ru.pravvich.servlets.Paths.ADMIN_MENU;
import static ru.pravvich.servlets.Paths.FIND;

public class AdminHomePageServletTest {

    @Test
    public void whenAttributeActEqualsEmptyStringThenMoveToMenuAgain()
            throws ServletException, IOException {

        final HttpServletRequest req = mock(HttpServletRequest.class);

        when(req.getParameterValues("act")).thenReturn(new String[]{""});

        when(req.getRequestDispatcher(ADMIN_MENU.get()))
                .thenReturn(mock(RequestDispatcher.class));


        final HttpServletResponse resp = mock(HttpServletResponse.class);


        final AdminHomePageServlet servlet = new AdminHomePageServlet();

        servlet.doPost(req, resp);

        verify(req).getRequestDispatcher(ADMIN_MENU.get());
    }

    @Test
    public void whenAttributeActEqualsGetAllThenSendToGetAllPage()
            throws ServletException, IOException {

        final HttpServletRequest req = mock(HttpServletRequest.class);

        when(req.getParameterValues("act")).thenReturn(new String[]{"all"});

        when(req.getRequestDispatcher("/getall"))
                .thenReturn(mock(RequestDispatcher.class));


        final HttpServletResponse resp = mock(HttpServletResponse.class);


        final AdminHomePageServlet servlet = new AdminHomePageServlet();

        servlet.doPost(req, resp);

        verify(req).getRequestDispatcher("/getall");
    }

    @Test
    public void whenAttributeActEqualsFindAllThenSendToFindPage()
            throws ServletException, IOException {

        final HttpServletRequest req = mock(HttpServletRequest.class);

        when(req.getParameterValues("act"))
                .thenReturn(new String[]{"find"});

        when(req.getRequestDispatcher(FIND.get()))
                .thenReturn(mock(RequestDispatcher.class));


        final HttpServletResponse resp = mock(HttpServletResponse.class);


        final AdminHomePageServlet servlet = new AdminHomePageServlet();

        servlet.doPost(req, resp);

        verify(req).getRequestDispatcher(FIND.get());
    }
}