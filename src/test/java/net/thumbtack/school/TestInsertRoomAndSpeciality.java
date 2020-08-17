package net.thumbtack.school;

import net.thumbtack.school.models.Room;
import net.thumbtack.school.models.Speciality;
import org.junit.Test;

import static org.junit.Assert.*;

import net.thumbtack.school.serviceClasses.State;

import java.util.ArrayList;
import java.util.List;

public class TestInsertRoomAndSpeciality extends TestDataBase {

    public List<Room> roomsFactory() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101"));
        rooms.add(new Room("101A"));
        rooms.add(new Room("102"));
        rooms.add(new Room("103"));
        rooms.add(new Room("104"));
        rooms.add(new Room("105"));
        rooms.add(new Room("106"));
        rooms.add(new Room("201"));
        rooms.add(new Room("202"));
        rooms.add(new Room("203"));
        rooms.add(new Room("203A"));
        rooms.add(new Room("204"));
        rooms.add(new Room("205"));
        rooms.add(new Room("206"));
        return rooms;
    }

    @Test
    public void testInsertRooms() {
        try {
            List<Room> rooms = roomsFactory();
            roomAndSpecialtiesDao.initializationRooms(rooms);
            for (Room room : rooms) {
                assertNotEquals(0, room.getId());
            }
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetRooms() {

        try {
            List<Room> rooms = roomsFactory();
            roomAndSpecialtiesDao.initializationRooms(rooms);
            List<Room> roomsFromDB = roomAndSpecialtiesDao.getAllRooms();
            assertEquals(rooms, roomsFromDB);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndChangeStatusRooms() {
        try {
            List<Room> rooms = roomsFactory();
            roomAndSpecialtiesDao.initializationRooms(rooms);
            List<Room> roomsFromDB = roomAndSpecialtiesDao.getAllRooms();
            roomsFromDB.get(0).setState(State.BUSY);
            Room busyRoom = roomAndSpecialtiesDao.setStatusRoom(roomsFromDB.get(0));
            assertEquals(busyRoom.getState(), State.BUSY);
            roomAndSpecialtiesDao.deleteAllRooms();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertAndGetAllFreeRooms() {
        try {
            List<Room> rooms = roomsFactory();
            roomAndSpecialtiesDao.initializationRooms(rooms);
            List<Room> roomsFromDB = roomAndSpecialtiesDao.getAllRooms();
            assertEquals(roomsFromDB.size(), 14);
            roomsFromDB.get(0).setState(State.BUSY);
            roomAndSpecialtiesDao.setStatusRoom(roomsFromDB.get(0));
            List<Room> freeRoomFromDB = roomAndSpecialtiesDao.getAllFreeRooms();
            assertEquals(freeRoomFromDB.size(), 13);
            roomAndSpecialtiesDao.deleteAllRooms();
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetNameSpecialityById () {
       try {
           String nameSpeciality = roomAndSpecialtiesDao.getNameSpecialityById(1);
           assertEquals(nameSpeciality, "Аллерголог");
       } catch (RuntimeException ex) {
           fail();
       }
    }

    @Test
    public void testGetAllSpecialties() {
        try {
            List<Speciality> specialtiesFromDB = roomAndSpecialtiesDao.getAllSpecialties();
            assertEquals(specialtiesFromDB.size(), 12);
        } catch (RuntimeException ex) {
            fail();
        }
    }

//    @Test
//    public void testGetSpeciality() {
//        try {
////            List<Speciality> specialties = specialtiesFactory();
////            roomAndSpecialtiesDao.initializationSpeciality(specialties);
//            Speciality specialityFromDB = roomAndSpecialtiesDao.getSpeciality(MedicalSpecialties.THERAPIST);
//            assertEquals(specialityFromDB.getSpeciality(), MedicalSpecialties.THERAPIST);
//        } catch (RuntimeException ex) {
//            fail();
//        }
//    }
}