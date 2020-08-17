package net.thumbtack.school.mappers;

import net.thumbtack.school.models.Schedule;
import net.thumbtack.school.models.ScheduleItem;
import net.thumbtack.school.serviceClasses.State;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AppointmentMapper {

    @Insert("INSERT INTO `appointment` (id_appointment, scheduleId, state, time_start, time_end) VALUES (#{scheduleItem.id}, #{schedule.id}, #{scheduleItem.state}, #{scheduleItem.timeStart}, #{scheduleItem.timeEnd})")
    @Options(useGeneratedKeys = true, keyProperty = "scheduleItem.id")
    Integer insert(@Param("scheduleItem") ScheduleItem scheduleItem, @Param("schedule") Schedule schedule);

    @Select("SELECT id_appointment, scheduleId, state, time_start, time_end FROM appointment WHERE id_appointment = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id_appointment"),
            @Result(property = "schedule", column = "scheduleId", javaType = Schedule.class,
                    one = @One(select = "net.thumbtack.school.mappers.ScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "state", column = "state"),
            @Result(property = "timeStart", column = "time_start"),
            @Result(property = "timeEnd", column = "time_end")
    })
    ScheduleItem getById(int id);

    @Select("SELECT id_appointment, scheduleId, state, time_start, time_end FROM appointment")
    @Results(value = {
            @Result(property = "id", column = "id_appointment"),
            @Result(property = "schedule", column = "scheduleId", javaType = Schedule.class,
                    one = @One(select = "net.thumbtack.school.mappers.ScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "state", column = "state"),
            @Result(property = "timeStart", column = "time_start"),
            @Result(property = "timeEnd", column = "time_end")
    })
    List<ScheduleItem> getAll();

    @Select("SELECT id_appointment, scheduleId, state, time_start, time_end FROM appointment WHERE scheduleId = #{schedule.id}")
    @Results(value = {
            @Result(property = "id", column = "id_appointment"),
            @Result(property = "schedule", column = "scheduleId", javaType = Schedule.class,
                    one = @One(select = "net.thumbtack.school.mappers.ScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "state", column = "state"),
            @Result(property = "timeStart", column = "time_start"),
            @Result(property = "timeEnd", column = "time_end")
    })
    List<ScheduleItem> getAllByIdSchedule(@Param("schedule") Schedule schedule);

    @Select("SELECT id_appointment, scheduleId, state, time_start, time_end FROM appointment WHERE scheduleId = #{schedule.id} AND state = #{state}")
    @Results(value = {
            @Result(property = "id", column = "id_appointment"),
            @Result(property = "schedule", column = "scheduleId", javaType = Schedule.class,
                    one = @One(select = "net.thumbtack.school.mappers.ScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "state", column = "state"),
            @Result(property = "timeStart", column = "time_start"),
            @Result(property = "timeEnd", column = "time_end")
    })
    List<ScheduleItem> getAllByIdScheduleAndState(@Param("schedule") Schedule schedule, @Param("state") State state);

    @Update("UPDATE appointment SET scheduleId = #{schedule.id}, state = #{scheduleItem.state}, time_start = #{scheduleItem.timeStart}, time_end = #{scheduleItem.timeEnd} WHERE id_appointment = #{scheduleItem.id}")
    void update(@Param("scheduleItem") ScheduleItem scheduleItem, @Param("schedule") Schedule schedule);

    @Delete("DELETE FROM appointment WHERE id_appointment = #{id}")
    void delete(ScheduleItem scheduleItem);

    @Delete("DELETE FROM appointment")
    void deleteAll();
}