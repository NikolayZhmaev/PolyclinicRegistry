package net.thumbtack.school.dao;

import net.thumbtack.school.models.Schedule;

import java.util.List;

public interface ScheduleDao {

    // inserts the Schedule into the database.
    Schedule insert(Schedule schedule);

    //retrieves the Schedule from the database by medical speciality
    Schedule getBySpeciality(String speciality);

    //retrieves the Schedule from the database by id
    Schedule getById(int id);

    //retrieves the Schedule from the database by id doctor
    List<Schedule> getByIdDoctor(int id);

    // changes the Schedule in the database
    Schedule update(Schedule schedule);

    // retrieves a list of all Schedule
    List<Schedule> getAll();

    //insert a list of Schedules
    void batchInsert(List<Schedule> schedules);

    // deletes a Schedule in the database
    void delete(Schedule schedule);

    // deletes all Schedules in the database
    void deleteAll();
}