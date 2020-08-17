package net.thumbtack.school.dao;

import net.thumbtack.school.models.Ticket;

import java.util.List;

public interface TicketDao {

    // inserts the Ticket into the database.
    Ticket insert(Ticket ticket);

    //retrieves the Ticket from the database by id
    Ticket getById(int id);

    //retrieves the Tickets from the database by id patient
    List<Ticket> getByIdPatient(int id);

    // changes the Ticket in the database
    Ticket update(Ticket ticket);

    // retrieves a list of all Ticket
    List<Ticket> getAll();

    //insert a list of Tickets
    void batchInsert(List<Ticket> tickets);

    // deletes a Ticket in the database
    void delete(Ticket ticket);

    // deletes all Tickets in the database
    void deleteAll();
}