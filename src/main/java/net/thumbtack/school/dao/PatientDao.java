package net.thumbtack.school.dao;

import net.thumbtack.school.models.Patient;

import java.util.List;

public interface PatientDao {

    // inserts the Patient into the database.
    Patient insert(Patient patient);

    //retrieves the Patient from the database by username
    Patient getByLogin(String login);

    // changes the Patient in the database
    Patient update(Patient patient);

    // retrieves a list of all Patients
    List<Patient> getAll();

    //insert a list of Patients
    void batchInsert(List<Patient> patient);

    // deletes a Patient in the database
    void delete(Patient patient);

    // deletes all Patients in the database
    void deleteAll();
}