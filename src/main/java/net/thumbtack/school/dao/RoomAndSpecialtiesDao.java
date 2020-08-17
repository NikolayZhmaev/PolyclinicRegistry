package net.thumbtack.school.dao;

import net.thumbtack.school.models.Room;
import net.thumbtack.school.models.Speciality;

import java.util.List;

//this class will initialize immutable service data

public interface RoomAndSpecialtiesDao {

    //this method creates a table of rooms
    List<Room> initializationRooms(List<Room> rooms);

    //this method creates a table of specialties
    void initializationSpeciality(List<String> specialities);

    //getting all specialties
    List<Speciality> getAllSpecialties();

    //change the status of the room
    Room setStatusRoom(Room room);

    //getting all free rooms
    List<Room> getAllFreeRooms();

    //getting all rooms
    List<Room> getAllRooms();

    //getting all doctors of the specialty
    List<Speciality> getDoctorsSpeciality(String speciality);

    //getting name of speciality dy id
    String getNameSpecialityById(int id);

    void deleteAllRooms();

    void deleteAllSpecialties();
}