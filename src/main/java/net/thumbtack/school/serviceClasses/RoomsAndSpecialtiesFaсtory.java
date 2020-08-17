package net.thumbtack.school.serviceClasses;

import net.thumbtack.school.models.Room;
import net.thumbtack.school.models.Speciality;

import java.util.ArrayList;
import java.util.List;

public class RoomsAndSpecialtiesFaсtory {

    //by task: room numbers and specialty can't be edited using the API.
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Speciality> specialties = new ArrayList<>();

    public RoomsAndSpecialtiesFaсtory() {
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
    }

    public static List<Room> getRooms() {
        return rooms;
    }

    public static List<Speciality> getSpecialities() {
        return specialties;
    }
}