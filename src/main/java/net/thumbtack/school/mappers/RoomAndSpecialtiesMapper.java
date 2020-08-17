package net.thumbtack.school.mappers;

import net.thumbtack.school.models.Room;
import net.thumbtack.school.models.Speciality;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoomAndSpecialtiesMapper {

    @Insert("INSERT INTO `rooms` (number) VALUE (#{number})")
    @Options(useGeneratedKeys = true)
    Integer insertRoom(Room room);

    @Update("UPDATE rooms SET state = #{state} WHERE id_room = #{id}")
    void setStateRoom(Room room);

    @Select("SELECT id_room, number, state FROM rooms WHERE state = 'FREE'")
    List<Room> getAllFreeRooms();

    @Select("SELECT id_room as id, number, state FROM rooms WHERE id_room = #{id}")
    Room getRoomById(int id);

    @Insert("INSERT INTO `medical_specialties` (name_speciality) VALUE (#{speciality})")
    @Options(useGeneratedKeys = true)
    Integer insertSpeciality(String speciality);

    @Select("SELECT id_speciality as id, name_speciality as speciality FROM medical_specialties WHERE id_speciality = #{id}")
    Speciality getSpecialityById(int id);

    @Select("SELECT name_speciality FROM medical_specialties WHERE id_speciality = #{id}")
    String getNameSpecialityById(int id);

    @Select("SELECT id_speciality as id, name_speciality as speciality FROM medical_specialties")
    List<Speciality> getAllSpecialties();

    @Select("SELECT id_speciality as id, name_speciality as speciality FROM medical_specialties WHERE name_speciality = #{speciality}")
    List<Speciality> getSpeciality(String speciality);

    @Select("SELECT id_room as id, number  FROM rooms")
    List<Room> getAllRooms();

    @Delete("DELETE FROM rooms")
    void deleteAllRooms();

    @Delete("DELETE FROM medical_specialties")
    void deleteAllSpecialties();
}