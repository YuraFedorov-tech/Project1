package ru.yura.service;
/*
 *
 *@Data 29.01.2020
 *@autor Fedorov Yuri
 *@project CRUD
 *
 */

import ru.yura.dao.UserDaoJDBC;
import ru.yura.model.User;

import java.sql.Connection;
import java.util.List;

public class UserServiceJDBC {
    Connection connection;
    private static UserServiceJDBC userServiceJDBC;

    private UserServiceJDBC(Connection connection) {
        this.connection = connection;
    }

    public static UserServiceJDBC getInstance(Connection connection) {
        if (userServiceJDBC == null) {
            userServiceJDBC = new UserServiceJDBC(connection);
        }
        return userServiceJDBC;
    }

    public static UserServiceJDBC getUserServiceJDBC() {
        return userServiceJDBC;
    }

    public List<User> getAllusers() {
        return new UserDaoJDBC(connection).getAllUser();
    }

    public void create() {
        new UserDaoJDBC(connection).create();
    }

    public void addUser(User user) {
        new UserDaoJDBC(connection).add(user);
    }

    public void removeUser(Long id) {
        new UserDaoJDBC(connection).delete(id);
    }

    public void update(User user) {
        new UserDaoJDBC(connection).update(user);
    }

    public User findUserAtId(Long id) {
        return new UserDaoJDBC(connection).findUserAtId(id);
    }
}
