package net.thumbtack.school.dao;

import net.thumbtack.school.models.Schedule;
import net.thumbtack.school.models.ScheduleItem;
import net.thumbtack.school.serviceClasses.State;

import java.util.List;

public interface AppointmentDao {

    // inserts the ScheduleItem into the database.
    ScheduleItem insert(ScheduleItem scheduleItem);

    //retrieves the ScheduleItem from the database by id
    ScheduleItem getById(int id);

    //retrieves the ScheduleItems from the database by id Schedule
    List<ScheduleItem> getByIdSchedule(Schedule schedule);

    //retrieves the ScheduleItems from the database by id Schedule and state
    List<ScheduleItem> getByIdScheduleAndState(Schedule schedule, State state);

    // changes the ScheduleItem in the database
    ScheduleItem update(ScheduleItem scheduleItem);

    // retrieves a list of all ScheduleItem
    List<ScheduleItem> getAll();

    //insert a list of ScheduleItems
    void batchInsert(List<ScheduleItem> scheduleItems);

    // deletes a ScheduleItem in the database
    void delete(ScheduleItem scheduleItem);

    // deletes all ScheduleItems in the database
    void deleteAll();
}
