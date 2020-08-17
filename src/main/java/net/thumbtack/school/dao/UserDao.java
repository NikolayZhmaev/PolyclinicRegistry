package net.thumbtack.school.dao;

import net.thumbtack.school.models.User;

import java.util.List;

public interface UserDao {

    // inserts the User into the database.
    User insert(User user);

    //retrieves the User from the database by username
    User getByLogin(String login);

    //retrieves the User from the database by id
    User getById(int id);

    // changes the User in the database
    User update(User user);

    // retrieves a list of all Users
    List<User> getAll();

    //insert a list of Users
    void batchInsert(List<User> users);

    // deletes a User in the database
    void delete(User user);

    // deletes all Users in the database
    void deleteAll();
}