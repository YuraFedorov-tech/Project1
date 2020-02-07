package ru.yura.servlets;
/*
 *
 *@Data 31.01.2020
 *@autor Fedorov Yuri
 *@project CRUD1
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateUser")
public class UpdatingUser extends HttpServlet {
    private UserServiceJDBC userServiceJDBC;
    private Long id;

    @Override
    public void init() throws ServletException {
        userServiceJDBC = UserServiceJDBC.getUserServiceJDBC();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        id = Long.parseLong(idString);
        User user = userServiceJDBC.findUserAtId(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        req.setAttribute("usersInJDBC", users);
        req.getServletContext().getRequestDispatcher("/jsp/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String color = req.getParameter("color");
        String ageString = req.getParameter("age");
        int age = Integer.parseInt(ageString);
        User user = new User(id, name, color, age);
        userServiceJDBC.update(user);
        resp.sendRedirect(req.getContextPath() + "/work");
    }
}
