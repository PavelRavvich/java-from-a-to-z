package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.DBJointHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Set in servlet context database join.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        final DBJoint joint = new DBJointHandler(
                "database_scripts",
                "authentication_database");

        servletContext.setAttribute("db", joint);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        final Connection db = (Connection) servletContextEvent
                .getServletContext().getAttribute("db");

        try {

            db.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
