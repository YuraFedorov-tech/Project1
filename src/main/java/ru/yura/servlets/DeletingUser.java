package ru.yura.servlets;

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

/*
 *
 *@Data 31.01.2020
 *@autor Fedorov Yuri
 *@project CRUD1
 *
 */
@WebServlet("/deleteUser")
public class DeletingUser extends HttpServlet {
    private UserServiceJDBC userServiceJDBC;

    @Override
    public void init() throws ServletException {
        userServiceJDBC = UserServiceJDBC.getUserServiceJDBC();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] items = req.getParameterValues("Delete");
       //doPost(req,resp);
        String idString = req.getParameter("id");
        Long id = Long.parseLong(idString);
        userServiceJDBC.removeUser(id);
        resp.sendRedirect(req.getContextPath() + "/work");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] items = req.getParameterValues("Delete");
        List<User> users = userServiceJDBC.getAllusers();
        if (users == null)
            users = new ArrayList<>();
        for(User user : users)
        {
            for(String str : items)
            {
                if(str.equals(user.getId().toString()))
                {
                    userServiceJDBC.removeUser(user.getId());
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/work");
    }
}
