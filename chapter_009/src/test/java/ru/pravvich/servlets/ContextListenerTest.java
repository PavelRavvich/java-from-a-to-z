package ru.pravvich.servlets;

import org.junit.Test;
import ru.pravvich.jdbc.DBJoint;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class ContextListenerTest {

    @Test
    public void joinObjectInjectInServletContext() {

        final ServletContextEvent event = mock(ServletContextEvent.class);
        final ServletContext context = mock(ServletContext.class);
        when(event.getServletContext()).thenReturn(context);

        final ContextListener listener = new ContextListener();

        listener.contextInitialized(event);

        listener.contextDestroyed(event);

        verify(context).setAttribute(eq("db"), any(DBJoint.class));
    }
}