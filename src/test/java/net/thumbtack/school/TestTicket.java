package net.thumbtack.school;

import net.thumbtack.school.models.*;
import net.thumbtack.school.serviceClasses.State;
import org.junit.Test;
import net.thumbtack.school.serviceClasses.RoomsAndSpecialtiesFaсtory;
import net.thumbtack.school.serviceClasses.UserCategory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTicket extends TestDataBase {

    RoomsAndSpecialtiesFaсtory roomsAndSpecialitiesFaсtory = new RoomsAndSpecialtiesFaсtory();
    List<Room> rooms = new ArrayList<>();
    List<Speciality> specialities = new ArrayList<>();
    Doctor doctorIvanov = new Doctor();
    Doctor doctorPetrov = new Doctor();
    Schedule schedule = new Schedule();
    List<ScheduleItem> scheduleItems = new ArrayList<>();


    public void RoomsAndSpecialities() {
        rooms = roomsAndSpecialitiesFaсtory.getRooms();
        roomAndSpecialtiesDao.initializationRooms(rooms);
        specialities = roomAndSpecialtiesDao.getAllSpecialties();
        User userIvanov = new User(UserCategory.DOCTOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        User userPetrov = new User(UserCategory.PATIENT, "Петр", "Петров", "Иванович", "Petrov", "I2222");

        doctorIvanov = new Doctor(rooms.get(0), specialities.get(0), userIvanov);
        doctorPetrov = new Doctor(rooms.get(1), specialities.get(1), userPetrov);
        doctorDao.insert(doctorIvanov);
        doctorDao.insert(doctorPetrov);
        try {
            schedule = new Schedule(doctorIvanov, "2020-04-11", 300, "09:00", "15:00");
            ScheduleItem scheduleItem1 = new ScheduleItem(schedule, State.FREE, "09:00", "09:05");
            ScheduleItem scheduleItem2 = new ScheduleItem(schedule, State.BUSY, "09:05", "09:10");
            ScheduleItem scheduleItem3 = new ScheduleItem(schedule, State.FREE, "09:10", "09:15");
            scheduleDao.insert(schedule);
            scheduleItems.add(scheduleItem1);
            scheduleItems.add(scheduleItem2);
            scheduleItems.add(scheduleItem3);
            appointmentDao.batchInsert(scheduleItems);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertTicket() {
        try {
            RoomsAndSpecialities();

            User userPatient = new User(UserCategory.PATIENT, "Петр", "Петров", "Иванович", "Petrov", "I2222");
            Patient patientPetrov = new Patient("email", "address", "13-13-13", userPatient);
            patientDao.insert(patientPetrov);

            Ticket ticket = new Ticket(patientPetrov, scheduleItems.get(0));
            ticketDao.insert(ticket);
            assertNotEquals(ticket.getId(), 0);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetTicket() {
        try {
            RoomsAndSpecialities();

            User userPatient = new User(UserCategory.PATIENT, "Петр", "Петров", "Иванович", "Petrov", "I2222");
            Patient patientPetrov = new Patient("email", "address", "13-13-13", userPatient);
            patientDao.insert(patientPetrov);

            Ticket ticket = new Ticket(patientPetrov, scheduleItems.get(0));
            ticketDao.insert(ticket);
            Ticket ticketFromDB = ticketDao.getById(ticket.getId());
            assertEquals(ticketFromDB, ticket);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testBatchInsertAndGetTicketsByIdPatient() {
        try {
            RoomsAndSpecialities();

            User userSidorov = new User(UserCategory.PATIENT, "Николай", "Сидоров", "Иванович", "Sidorov", "I2222");
            Patient patientSidorov = new Patient("email", "address", "13-13-13", userSidorov);
            patientDao.insert(patientSidorov);

            Ticket ticketPetrov = new Ticket(patientSidorov, scheduleItems.get(0));
            Ticket ticketSidorov = new Ticket(patientSidorov, scheduleItems.get(1));
            List<Ticket> ticketsInsert = new ArrayList<>();
            ticketsInsert.add(ticketPetrov);
            ticketsInsert.add(ticketSidorov);

            ticketDao.batchInsert(ticketsInsert);
            List<Ticket> ticketsFromDB = ticketDao.getByIdPatient(patientSidorov.getId());

            assertEquals(ticketsFromDB, ticketsInsert);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testBatchInsertAndGetAllTickets() {
        try {
            RoomsAndSpecialities();

            User userSidorov = new User(UserCategory.PATIENT, "Николай", "Сидоров", "Иванович", "Sidorov", "I2222");
            Patient patientSidorov = new Patient("email", "address", "13-13-13", userSidorov);
            patientDao.insert(patientSidorov);

            Ticket ticketIvanov = new Ticket(patientSidorov, scheduleItems.get(0));
            Ticket ticketPetrov = new Ticket(patientSidorov, scheduleItems.get(1));
            List<Ticket> ticketsInsert = new ArrayList<>();
            ticketsInsert.add(ticketIvanov);
            ticketsInsert.add(ticketPetrov);

            ticketDao.batchInsert(ticketsInsert);
            List<Ticket> ticketsFromDB = ticketDao.getAll();
            assertEquals(ticketsFromDB, ticketsInsert);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndUpdateTicket() {
        try {
            RoomsAndSpecialities();

            User userPatient = new User(UserCategory.PATIENT, "Петр", "Петров", "Иванович", "Petrov", "I2222");
            Patient patientPetrov = new Patient("email", "address", "13-13-13", userPatient);
            patientDao.insert(patientPetrov);

            Ticket ticket = new Ticket(patientPetrov, scheduleItems.get(0));
            ticketDao.insert(ticket);

            ticket.setScheduleItem(scheduleItems.get(1));
            ticketDao.update(ticket);
            Ticket ticketFromDB = ticketDao.getById(ticket.getId());
            assertEquals(ticketFromDB, ticket);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndDeleteTicket() {
        try {
            RoomsAndSpecialities();

            User userPatient = new User(UserCategory.PATIENT, "Петр", "Петров", "Иванович", "Petrov", "I2222");
            Patient patientPetrov = new Patient("email", "address", "13-13-13", userPatient);
            patientDao.insert(patientPetrov);

            Ticket ticket = new Ticket(patientPetrov, scheduleItems.get(0));
            ticketDao.insert(ticket);
            ticketDao.delete(ticket);
            assertNull(ticketDao.getById(ticket.getId()));
        } catch (RuntimeException ex) {
            fail();
        }
    }
}