package net.thumbtack.school.models;

import java.util.Objects;

public class Ticket {
    private int id;
    private Patient patient;
    private ScheduleItem scheduleItem;

    public Ticket() {
    }

    public Ticket(int id, Patient patient, ScheduleItem scheduleItem) {
        this.id = id;
        this.patient = patient;
        this.scheduleItem = scheduleItem;
    }

    public Ticket(Patient patient, ScheduleItem scheduleItem) {
        this(0, patient, scheduleItem);
    }

    public Ticket(ScheduleItem scheduleItem) {
        this(0, null, scheduleItem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ScheduleItem getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(ScheduleItem scheduleItem) {
        this.scheduleItem = scheduleItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(patient, ticket.patient) &&
                Objects.equals(scheduleItem, ticket.scheduleItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, scheduleItem);
    }
}