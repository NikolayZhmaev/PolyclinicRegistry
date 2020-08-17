package net.thumbtack.school.dao;

import net.thumbtack.school.models.Administrator;

import java.util.List;

public interface AdministratorDao {

    // inserts the Administrator into the database.
    Administrator insert(Administrator administrator);

    //retrieves the Administrator from the database by username
    Administrator getByLogin(String login);

    // changes the Administrator in the database
    Administrator update(Administrator administrator);

    // retrieves a list of all Administrators
    List<Administrator> getAll();

    //insert a list of Administrators
    void batchInsert(List<Administrator> administrators);

    // deletes a Administrator in the database
    void delete(Administrator administrator);

    // deletes all Administrators in the database
    void deleteAll();
}