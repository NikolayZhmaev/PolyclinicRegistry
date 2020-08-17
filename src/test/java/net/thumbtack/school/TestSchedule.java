package net.thumbtack.school;

import net.thumbtack.school.models.*;
import org.junit.Test;
import net.thumbtack.school.serviceClasses.State;
import net.thumbtack.school.serviceClasses.RoomsAndSpecialtiesFaсtory;
import net.thumbtack.school.serviceClasses.UserCategory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSchedule extends TestDataBase {

    RoomsAndSpecialtiesFaсtory roomsAndSpecialitiesFaсtory = new RoomsAndSpecialtiesFaсtory();
    List<Room> rooms = new ArrayList<>();
    List<Speciality> specialities = new ArrayList<>();
    User user = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
    Doctor doctor = new Doctor();


    public void RoomsAndSpecialities() {
        rooms = roomsAndSpecialitiesFaсtory.getRooms();
        roomAndSpecialtiesDao.initializationRooms(rooms);
        specialities = roomAndSpecialtiesDao.getAllSpecialties();
        doctor = new Doctor(rooms.get(0), specialities.get(0), user);
        doctor.getRoom().setState(State.BUSY);
        doctorDao.insert(doctor);
    }

    @Test
    public void testInsertAndGetSchedule() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);
            assertNotEquals(schedule.getId(), 0);
            Schedule scheduleFromDB = scheduleDao.getById(schedule.getId());
            assertEquals(scheduleFromDB, schedule);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetAllSchedulesById() {
        try {
            RoomsAndSpecialities();
            Schedule schedule1 = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            Schedule schedule2 = new Schedule(doctor, "2020-04-12", 300, "09:00", "15:00");
            Schedule schedule3 = new Schedule(doctor, "2020-04-13", 300, "09:00", "15:00");

            List<Schedule> schedules = new ArrayList<>();
            schedules.add(schedule1);
            schedules.add(schedule2);
            schedules.add(schedule3);

            scheduleDao.batchInsert(schedules);
            assertNotEquals(schedules.get(0).getId(), 0);

            List<Schedule> schedulesFromDB = scheduleDao.getByIdDoctor(doctor.getId());
            assertEquals(schedulesFromDB, schedules);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetAllSchedules() {
        try {
            RoomsAndSpecialities();
            Schedule schedule1 = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            Schedule schedule2 = new Schedule(doctor, "2020-04-12", 300, "09:00", "15:00");
            Schedule schedule3 = new Schedule(doctor, "2020-04-13", 300, "09:00", "15:00");

            List<Schedule> schedules = new ArrayList<>();
            schedules.add(schedule1);
            schedules.add(schedule2);
            schedules.add(schedule3);

            scheduleDao.batchInsert(schedules);
            assertNotEquals(schedules.get(0).getId(), 0);

            List<Schedule> schedulesFromDB = scheduleDao.getAll();
            assertEquals(schedulesFromDB, schedules);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdateSchedule() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);
            schedule.setDate("2020-04-12");
            Schedule scheduleFromDB = scheduleDao.update(schedule);
            assertEquals(scheduleFromDB, schedule);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteSchedule() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);
            int id = schedule.getId();
            scheduleDao.delete(schedule);
            assertNull(scheduleDao.getById(id));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetScheduleItem() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);

            ScheduleItem scheduleItem = new ScheduleItem(schedule, State.FREE, "09:00", "09:05");
            appointmentDao.insert(scheduleItem);

            assertNotEquals(scheduleItem.getId(), 0);
            ScheduleItem scheduleItemFromDB = appointmentDao.getById(scheduleItem.getId());
            assertEquals(scheduleItemFromDB, scheduleItem);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetAllScheduleItemsById() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);

            ScheduleItem scheduleItem1 = new ScheduleItem(schedule, State.FREE, "09:00", "09:05");
            ScheduleItem scheduleItem2 = new ScheduleItem(schedule, State.FREE, "09:05", "09:10");
            ScheduleItem scheduleItem3 = new ScheduleItem(schedule, State.FREE, "09:10", "09:15");

            List<ScheduleItem> scheduleItems = new ArrayList<>();
            scheduleItems.add(scheduleItem1);
            scheduleItems.add(scheduleItem2);
            scheduleItems.add(scheduleItem3);

            appointmentDao.batchInsert(scheduleItems);
            assertNotEquals(scheduleItems.get(0).getId(), 0);

            List<ScheduleItem> scheduleItemsFromDB = appointmentDao.getByIdSchedule(schedule);
            assertEquals(scheduleItemsFromDB, scheduleItems);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetAllScheduleItems() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);

            ScheduleItem scheduleItem1 = new ScheduleItem(schedule, State.FREE, "09:00", "09:05");
            ScheduleItem scheduleItem2 = new ScheduleItem(schedule, State.FREE, "09:05", "09:10");
            ScheduleItem scheduleItem3 = new ScheduleItem(schedule, State.FREE, "09:10", "09:15");

            List<ScheduleItem> scheduleItems = new ArrayList<>();
            scheduleItems.add(scheduleItem1);
            scheduleItems.add(scheduleItem2);
            scheduleItems.add(scheduleItem3);

            appointmentDao.batchInsert(scheduleItems);
            assertNotEquals(scheduleItems.get(0).getId(), 0);

            List<ScheduleItem> scheduleItemsFromDB = appointmentDao.getAll();
            assertEquals(scheduleItemsFromDB, scheduleItems);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetAllFreeScheduleItemsBySchedule() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);

            ScheduleItem scheduleItem1 = new ScheduleItem(schedule, State.FREE, "09:00", "09:05");
            ScheduleItem scheduleItem2 = new ScheduleItem(schedule, State.BUSY, "09:05", "09:10");
            ScheduleItem scheduleItem3 = new ScheduleItem(schedule, State.FREE, "09:10", "09:15");

            List<ScheduleItem> scheduleItems = new ArrayList<>();
            scheduleItems.add(scheduleItem1);
            scheduleItems.add(scheduleItem2);
            scheduleItems.add(scheduleItem3);

            appointmentDao.batchInsert(scheduleItems);
            assertNotEquals(scheduleItems.get(0).getId(), 0);

            List<ScheduleItem> scheduleItemsFromDB = appointmentDao.getByIdScheduleAndState(schedule, State.FREE);
            assertEquals(scheduleItemsFromDB.size(), 2);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndUpdateScheduleItems() {
        try {
            RoomsAndSpecialities();
            Schedule schedule = new Schedule(doctor, "2020-04-11", 300, "09:00", "15:00");
            scheduleDao.insert(schedule);

            ScheduleItem scheduleItem1 = new ScheduleItem(schedule, State.FREE, "09:00", "09:05");
            ScheduleItem scheduleItem2 = new ScheduleItem(schedule, State.BUSY, "09:05", "09:10");
            ScheduleItem scheduleItem3 = new ScheduleItem(schedule, State.FREE, "09:10", "09:15");

            List<ScheduleItem> scheduleItems = new ArrayList<>();
            scheduleItems.add(scheduleItem1);
            scheduleItems.add(scheduleItem2);
            scheduleItems.add(scheduleItem3);

            appointmentDao.batchInsert(scheduleItems);
            assertNotEquals(scheduleItems.get(0).getId(), 0);

            scheduleItem1.setState(State.BUSY);
            appointmentDao.update(scheduleItem1);

            ScheduleItem scheduleItemFromDB = appointmentDao.getById(scheduleItem1.getId());
            assertEquals(scheduleItemFromDB.getState(), State.BUSY);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException ex) {
            fail();
        }
    }
}