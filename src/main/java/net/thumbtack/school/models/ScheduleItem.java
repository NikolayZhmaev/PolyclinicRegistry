package net.thumbtack.school.models;

import net.thumbtack.school.serviceClasses.State;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ScheduleItem {

    private int id;
    private Schedule schedule;
    private State state; // the status of the appointment
    private Date timeStart; // reception start time
    private Date timeEnd; // reception end time

    public ScheduleItem() {
    }

    public ScheduleItem(int id, Schedule schedule, State state, Date timeStart, Date timeEnd) {
        this.id = id;
        this.schedule = schedule;
        this.state = state;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public ScheduleItem(int id, Schedule schedule, State state, String timeStart, String timeEnd) throws ParseException {
        this.id = id;
        this.schedule = schedule;
        this.state = state;
        this.timeStart = new SimpleDateFormat("H:mm").parse(timeStart);
        this.timeEnd = new SimpleDateFormat("H:mm").parse(timeEnd);
    }

    public ScheduleItem(Schedule schedule, State state, String timeStart, String timeEnd) throws ParseException {
        this(0, schedule, state, timeStart, timeEnd);
    }

    public ScheduleItem(Schedule schedule, State state, Date timeStart, Date timeEnd) {
        this(0, schedule, state, timeStart, timeEnd);
    }

    public ScheduleItem(State state, Date timeStart, Date timeEnd) {
        this(0, null, state, timeStart, timeEnd);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule scheduleId) {
        this.schedule = scheduleId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduleItem)) return false;
        ScheduleItem that = (ScheduleItem) o;
        return id == that.id &&
                Objects.equals(schedule, that.schedule) &&
                state == that.state &&
                Objects.equals(timeStart, that.timeStart) &&
                Objects.equals(timeEnd, that.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schedule, state, timeStart, timeEnd);
    }
}