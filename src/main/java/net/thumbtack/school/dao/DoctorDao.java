package net.thumbtack.school.dao;

import net.thumbtack.school.models.Doctor;

import java.util.List;

public interface DoctorDao {

    // inserts the Doctor into the database.
    Doctor insert(Doctor doctor);

    //retrieves the Doctor from the database by username
    Doctor getByLogin(String login);

    // changes the Doctor in the database
    Doctor update(Doctor doctor);

    // retrieves a list of all Doctors
    List<Doctor> getAll();

    //insert a list of Doctors
    void batchInsert(List<Doctor> doctors);

    // deletes a Doctor in the database
    void delete(Doctor doctor);

    // deletes all Doctors in the database
    void deleteAll();
}
