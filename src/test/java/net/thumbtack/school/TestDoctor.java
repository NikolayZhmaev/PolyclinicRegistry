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

public class TestDoctor extends TestDataBase {

    RoomsAndSpecialtiesFaсtory roomsAndSpecialitiesFaсtory = new RoomsAndSpecialtiesFaсtory();
    List<Room> rooms = new ArrayList<>();
    List<Speciality> specialities = new ArrayList<>();

    public void RoomsAndSpecialities() {
        rooms = roomsAndSpecialitiesFaсtory.getRooms();
        roomAndSpecialtiesDao.initializationRooms(rooms);
        specialities = roomAndSpecialtiesDao.getAllSpecialties();

    }

    @Test
    public void testInsertDoctorWithRoomAndSpeciality() {
        try {
            RoomsAndSpecialities();
            User user = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Doctor doctor = new Doctor(rooms.get(0), specialities.get(0), user);
            doctor.getRoom().setState(State.BUSY);
            Doctor doctorFromDB = doctorDao.insert(doctor);
            assertNotEquals(0, doctorFromDB.getId());
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetDoctorByLogin() {
        try {
            RoomsAndSpecialities();
            User user = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Doctor doctor = new Doctor(rooms.get(0), specialities.get(0), user);
            doctor.getRoom().setState(State.BUSY);
            Doctor insertDoctor = doctorDao.insert(doctor);
            Doctor foundDoctor = doctorDao.getByLogin("Ivanov");
            assertEquals(foundDoctor, insertDoctor);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdateDoctor() {
        try {
            RoomsAndSpecialities();
            User user = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Doctor doctor = new Doctor(rooms.get(0), specialities.get(0), user);
            doctor.getRoom().setState(State.BUSY);
            Doctor insertDoctor = doctorDao.insert(doctor);
            assertEquals(insertDoctor, doctor);
            user.setFirstName("Евгений");
            Doctor foundDoctor = doctorDao.update(doctor);
            assertEquals(foundDoctor, insertDoctor);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testBatchInsertAndGetAllDoctors() {
        try {
            RoomsAndSpecialities();

            User userIvanov = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            User userPetrov = new User(UserCategory.DOCTOR, "Петр", "Петров", "Иванович", "Petrov", "I2222");
            User userSidorov = new User(UserCategory.DOCTOR, "Николай", "Сидоров", "Иванович", "Sidorov", "I2222");

            Doctor doctorIvanov = new Doctor(rooms.get(0), specialities.get(0), userIvanov);
            Doctor doctorPetrov = new Doctor(rooms.get(1), specialities.get(1), userPetrov);
            Doctor doctorSidorov = new Doctor(rooms.get(2), specialities.get(2), userSidorov);

            List<Doctor> insertDoctors = new ArrayList<>();
            insertDoctors.add(doctorIvanov);
            insertDoctors.add(doctorPetrov);
            insertDoctors.add(doctorSidorov);

            doctorDao.batchInsert(insertDoctors);
            List<Doctor> foundDoctors = doctorDao.getAll();

            assertEquals(foundDoctors, insertDoctors);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteDoctor() {
        try {
            RoomsAndSpecialities();
            User userIvanov = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Doctor doctorIvanov = new Doctor(rooms.get(0), specialities.get(0), userIvanov);
            doctorDao.insert(doctorIvanov);
            Doctor doctorFromDB = doctorDao.getByLogin("Ivanov");
            assertEquals(doctorFromDB, doctorIvanov);
            doctorDao.delete(doctorIvanov);
            assertNull(patientDao.getByLogin("Ivanov"));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertDoctorWithRoomSpecialityAndSchedules() {
        try {
            RoomsAndSpecialities();
            User user = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");

            List<Schedule> schedules = new ArrayList<>();
            Schedule schedule1 = new Schedule("2020-04-11", 300, "09:00", "15:00", null);
            Schedule schedule2 = new Schedule("2020-04-12", 300, "09:00", "15:00", null);
            Schedule schedule3 = new Schedule("2020-04-13", 300, "09:00", "15:00", null);
            schedules.add(schedule1);
            schedules.add(schedule2);
            schedules.add(schedule3);

            Doctor doctor = new Doctor(rooms.get(0), specialities.get(0), user, schedules);

            doctor.getRoom().setState(State.BUSY);

            doctorDao.insert(doctor);
            assertNotEquals(0, doctor.getId());
            Doctor doctorFromDB = doctorDao.getByLogin("Ivanov");
            assertEquals(doctorFromDB, doctor);
        } catch (RuntimeException ex) {
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}