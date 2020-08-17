package net.thumbtack.school.models;

import java.util.Objects;

public class Patient {
    private int id;
    private String email;
    private String address;
    private String phone;
    private User user;

    public Patient() {
    }

    public Patient(int id, String email, String address, String phone, User user) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.user = user;
    }

    public Patient(String email, String address, String phone, User user) {
        this(0, email, address, phone, user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                Objects.equals(email, patient.email) &&
                Objects.equals(address, patient.address) &&
                Objects.equals(phone, patient.phone) &&
                Objects.equals(user, patient.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, address, phone, user);
    }
}