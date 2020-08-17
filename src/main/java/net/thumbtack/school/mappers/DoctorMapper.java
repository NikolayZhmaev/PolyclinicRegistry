package net.thumbtack.school.mappers;

import net.thumbtack.school.models.Doctor;
import net.thumbtack.school.models.Room;
import net.thumbtack.school.models.Speciality;
import net.thumbtack.school.models.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface DoctorMapper {

    @Insert("INSERT INTO `doctors` (user_id, room, speciality) VALUES (#{user.id}, #{room.id}, #{speciality.id})")
    @Options(useGeneratedKeys = true, keyProperty = "doctor.id")
    Integer insert(@Param("doctor") Doctor doctor, @Param("user") User user, @Param("room") Room room,
                   @Param("speciality") Speciality speciality);

    @Select("SELECT id_doctor, user_id, room, speciality FROM doctors WHERE user_id = #{id_user}")
    @Results(value = {
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT)),
            @Result(property = "room", column = "room", javaType = Room.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getRoomById", fetchType = FetchType.DEFAULT)),
            @Result(property = "speciality", column = "speciality", javaType = Speciality.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getSpecialityById", fetchType = FetchType.LAZY)),
            @Result(property = "schedules", column = "id_doctor", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.mappers.ScheduleMapper.getAllByDoctorId", fetchType = FetchType.LAZY))
    })
    Doctor getByIdUser(int id_user);

    @Select("SELECT id_doctor, user_id, room, speciality FROM doctors WHERE id_doctor = #{id_doctor}")
    @Results(value = {
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT)),
            @Result(property = "room", column = "room", javaType = Room.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getRoomById", fetchType = FetchType.DEFAULT)),
            @Result(property = "speciality", column = "speciality", javaType = Speciality.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getSpecialityById", fetchType = FetchType.DEFAULT)),
            @Result(property = "schedules", column = "id_doctor", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.mappers.ScheduleMapper.getAllByDoctorId", fetchType = FetchType.LAZY))
    })
    Doctor getById(int id_doctor);

    @Select("SELECT id_doctor, user_id, room, speciality FROM doctors WHERE speciality = #{id_speciality}")
    @Results(value = {
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT)),
            @Result(property = "room", column = "room", javaType = Room.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getRoomById", fetchType = FetchType.DEFAULT)),
            @Result(property = "speciality", column = "speciality", javaType = Speciality.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getSpecialityById", fetchType = FetchType.DEFAULT)),
            @Result(property = "schedules", column = "id_doctor", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.mappers.ScheduleMapper.getAllByDoctorId", fetchType = FetchType.LAZY))
    })
    Doctor getBySpeciality(int id_speciality);


    @Update("UPDATE doctors SET room = #{room.id}, speciality = #{speciality.id} WHERE user_id = #{user.id}")
    void update(@Param("doctor") Doctor doctor, @Param("user") User user, @Param("room") Room room, @Param("speciality") Speciality speciality);

    @Select("SELECT id_doctor, user_id, room, speciality FROM doctors")
    @Results(value = {
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT)),
            @Result(property = "room", column = "room", javaType = Room.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getRoomById", fetchType = FetchType.DEFAULT)),
            @Result(property = "speciality", column = "speciality", javaType = Speciality.class,
                    one = @One(select = "net.thumbtack.school.mappers.RoomAndSpecialtiesMapper.getSpecialityById", fetchType = FetchType.DEFAULT)),
    })
    List<Doctor> getAll();

    @Delete("DELETE FROM doctors")
    void deleteAll();
}