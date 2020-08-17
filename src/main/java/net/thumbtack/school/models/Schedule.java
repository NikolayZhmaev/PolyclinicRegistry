package net.thumbtack.school.models;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Schedule {
    private int id;
    private Doctor doctor;
    private Date date;
    private int durationReception; // duration of one patient appointment
    private Date timeStart; // time start of the working day
    private Date timeEnd; // the end time of the working day
    private List<ScheduleItem> scheduleItem = new ArrayList<>(); // appointments during the day

    public Schedule() {
    }

    public Schedule(int id, Doctor doctor, String date, int durationReception, String timeStart, String timeEnd, List<ScheduleItem> scheduleItem) throws ParseException {
        this.id = id;
        this.doctor = doctor;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.durationReception = durationReception;
        this.timeStart = new SimpleDateFormat("H:mm").parse(timeStart);
        this.timeEnd = new SimpleDateFormat("H:mm").parse(timeEnd);
        this.scheduleItem = scheduleItem;
    }

    public Schedule(Doctor doctor, String date, int durationReception, String timeStart, String timeEnd, List<ScheduleItem> scheduleItem) throws ParseException {
        this(0, doctor, date, durationReception, timeStart, timeEnd, scheduleItem);
    }

    public Schedule(String date, int durationReception, String timeStart, String timeEnd, List<ScheduleItem> scheduleItem) throws ParseException {
        this(0, null, date, durationReception, timeStart, timeEnd, scheduleItem);
    }

    public Schedule(Doctor doctor, String date, int durationReception, String timeStart, String timeEnd) throws ParseException {
        this.id = 0;
        this.doctor = doctor;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.durationReception = durationReception;
        this.timeStart = new SimpleDateFormat("H:mm").parse(timeStart);
        this.timeEnd = new SimpleDateFormat("H:mm").parse(timeEnd);
    }

    public Schedule(int id, Doctor doctor, Date date, int durationReception, Time timeStart, Time timeEnd) {
        this.id = id;
        this.doctor = doctor;
        this.date = date;
        this.durationReception = durationReception;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Schedule(Doctor doctor, Date date, int durationReception, Date timeStart, Date timeEnd) {
        this.id = 0;
        this.doctor = doctor;
        this.date = date;
        this.durationReception = durationReception;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public int getDurationReception() {
        return durationReception;
    }

    public void setDurationReception(int durationReception) {
        this.durationReception = durationReception;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public List<ScheduleItem> getScheduleItem() {
        return scheduleItem;
    }

    public void setSheduleItem(List<ScheduleItem> scheduleItems) {
        this.scheduleItem = scheduleItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                durationReception == schedule.durationReception &&
                Objects.equals(doctor, schedule.doctor) &&
                Objects.equals(date, schedule.date) &&
                Objects.equals(timeStart, schedule.timeStart) &&
                Objects.equals(timeEnd, schedule.timeEnd) &&
                Objects.equals(scheduleItem, schedule.scheduleItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, date, durationReception, timeStart, timeEnd, scheduleItem);
    }
}