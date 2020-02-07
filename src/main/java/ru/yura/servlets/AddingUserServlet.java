package ru.yura.servlets;
/*
 *
 *@Data 30.01.2020
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
import java.io.IOException;
import java.util.List;

@WebServlet("/addUser")
public class AddingUserServlet extends HttpServlet {
    private UserServiceJDBC userServiceJDBC;

    @Override
    public void init() throws ServletException {
        userServiceJDBC = UserServiceJDBC.getUserServiceJDBC();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userServiceJDBC.getAllusers();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String color = req.getParameter("color");
        String ageString = req.getParameter("age");
        int age = Integer.parseInt(ageString);
        User user = new User(name, color, age);
        userServiceJDBC.addUser(user);
        resp.sendRedirect(req.getContextPath() + "/work");
    }
}
