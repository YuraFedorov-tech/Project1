package ru.yura.servlets;
/*
 *
 *@Data 29.01.2020
 *@autor Fedorov Yuri
 *@project CRUD
 *
 */

import ru.yura.model.User;
import ru.yura.service.UserServiceJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/work")
public class MainServlet extends HttpServlet {
    private UserServiceJDBC userServiceJDBC;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         List<User> users = userServiceJDBC.getAllusers();
        if (users == null)
            users = new ArrayList<>();
        req.setAttribute("usersInJDBC", users);
        req.getServletContext().getRequestDispatcher("/jsp/crudUsers.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        Connection connection;
        try {
            String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
            String dbPassword = "123";
            String dbUserName = "postgres";
            String driverClassName = "org.postgresql.Driver";
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            userServiceJDBC = UserServiceJDBC.getInstance(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException();
        }
        userServiceJDBC.create();
        System.out.println("init JDBC");
    }
}
