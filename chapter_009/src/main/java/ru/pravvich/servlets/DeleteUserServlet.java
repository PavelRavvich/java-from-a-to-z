package ru.pravvich.servlets;

import ru.pravvich.jdbc.DBJoint;
import ru.pravvich.jdbc.DBJointHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Delete user from database by id..
 */
public class DeleteUserServlet extends HttpServlet {

    private DBJoint db;

    public DeleteUserServlet() {
        super();
        db = new DBJointHandler("database_scripts", "authentication_database");
    }

    public void setDb(DBJoint db) {
        this.db = db;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            delUserFromDB(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delUserFromDB(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        final int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);

        //db.getDBExecutor().deleteUser(new User());

        String answer;

        if (id <= 0) {
            answer = "Пользователя с таким id не существует";
        } else {
            answer = String.format("%s %s %s", "Пользователь с id =", id, "удален.");
        }

        req.setAttribute("serverAnswer", answer);
        req.getRequestDispatcher("answer.jsp").forward(req, resp);


    }
}
