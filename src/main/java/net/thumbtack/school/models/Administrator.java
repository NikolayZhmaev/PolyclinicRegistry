package net.thumbtack.school.models;

import java.util.Objects;

public class Administrator {
    private int id;
    private String position;
    private User user;

    public Administrator() {
    }

    public Administrator(int id, String position, User user) {
        this.id = id;
        this.position = position;
        this.user = user;
    }

    public Administrator(String position, User user) {
        this(0, position, user);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        Administrator that = (Administrator) o;
        return id == that.id &&
                position.equals(that.position) &&
                user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, user);
    }
}