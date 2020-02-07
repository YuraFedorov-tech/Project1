package ru.yura.dao;

import ru.yura.model.User;

import java.util.List;

/*
 *
 *@Data 29.01.2020
 *@autor Fedorov Yuri
 *@project CRUD
 *
 */
public interface CrudDAO<T> {
    void add(T model);

    void update(T model);

    void delete(Long id);

    List<T> getAllUser();

    void create();

    User findUserAtId(Long id);
}
