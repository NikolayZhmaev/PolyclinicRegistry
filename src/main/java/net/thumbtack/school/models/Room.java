package net.thumbtack.school.models;

import net.thumbtack.school.serviceClasses.State;

import java.util.Objects;

public class Room {
    private int id;
    private String number;
    private State state;

    public Room() {
    }

    public Room(int id, String number, State state) {
        this.id = id;
        this.number = number;
        this.state = state;
    }

    public Room(String number) {
        this(0, number, State.FREE);
    }

    public int getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return id == room.id &&
                Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }
}