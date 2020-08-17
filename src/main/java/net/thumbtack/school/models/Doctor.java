package net.thumbtack.school.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor {

    private int id;
    private Room room;
    private Speciality speciality;
    private User user;
    private List<Schedule> schedules = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(int id, Room room, Speciality speciality, User user) {
        this.id = id;
        this.room = room;
        this.speciality = speciality;
        this.user = user;
    }

    public Doctor(int id, Room room, Speciality speciality, User user, List<Schedule> schedules) {
        this.id = id;
        this.room = room;
        this.speciality = speciality;
        this.user = user;
        this.schedules = schedules;
    }

    public Doctor(Room room, Speciality speciality, User user) {
        this(0, room, speciality, user);
    }

    public Doctor(Room room, Speciality speciality, User user, List<Schedule> schedules) {
        this(0, room, speciality, user, schedules);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id &&
                Objects.equals(room, doctor.room) &&
                Objects.equals(speciality, doctor.speciality) &&
                Objects.equals(user, doctor.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room, speciality, user);
    }
}