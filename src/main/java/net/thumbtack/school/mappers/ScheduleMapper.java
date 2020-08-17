package net.thumbtack.school.mappers;

import net.thumbtack.school.models.Doctor;
import net.thumbtack.school.models.Schedule;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ScheduleMapper {

    @Insert("INSERT INTO `schedule` (doctor_id, date, duration_reception, time_start, time_end) VALUES (#{doctor.id}, #{schedule.date}, #{schedule.durationReception}, #{schedule.timeStart}, #{schedule.timeEnd})")
    @Options(useGeneratedKeys = true, keyProperty = "schedule.id")
    Integer insert(@Param("schedule") Schedule schedule, @Param("doctor") Doctor doctor);

    @Select("SELECT id_schedule, doctor_id, date, duration_reception, time_start, time_end FROM schedule WHERE id_schedule = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id_schedule"),
            @Result(property = "doctor", column = "doctor_id", javaType = Doctor.class,
                    one = @One(select = "net.thumbtack.school.mappers.DoctorMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "date", column = "date"),
            @Result(property = "durationReception", column = "duration_reception"),
            @Result(property = "timeStart", column = "time_start"),
            @Result(property = "timeEnd", column = "time_end")
    })
    Schedule getById(int id);

    @Select("SELECT id_schedule, doctor_id, date, duration_reception, time_start, time_end FROM schedule WHERE doctor_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id_schedule"),
            @Result(property = "doctor", column = "doctor_id", javaType = Doctor.class,
                    one = @One(select = "net.thumbtack.school.mappers.DoctorMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "date", column = "date"),
            @Result(property = "durationReception", column = "duration_reception"),
            @Result(property = "timeStart", column = "time_start"),
            @Result(property = "timeEnd", column = "time_end")
    })
    List<Schedule> getAllByDoctorId(int id);

    @Select("SELECT id_schedule, doctor_id, date, duration_reception, time_start, time_end FROM schedule")
    @Results(value = {
            @Result(property = "id", column = "id_schedule"),
            @Result(property = "doctor", column = "doctor_id", javaType = Doctor.class,
                    one = @One(select = "net.thumbtack.school.mappers.DoctorMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "date", column = "date"),
            @Result(property = "durationReception", column = "duration_reception"),
            @Result(property = "timeStart", column = "time_start"),
            @Result(property = "timeEnd", column = "time_end")
    })
    List<Schedule> getAll();

    @Update("UPDATE schedule SET doctor_id = #{doctor.id}, date = #{schedule.date}, duration_reception = #{schedule.durationReception}, time_start = #{schedule.timeStart}, time_end = #{schedule.timeEnd} WHERE id_schedule = #{schedule.id}")
    void update(@Param("schedule") Schedule schedule, @Param("doctor") Doctor doctor);

    @Delete("DELETE FROM schedule WHERE id_schedule = #{id}")
    void delete(Schedule schedule);

    @Delete("DELETE FROM schedule")
    void deleteAll();
}