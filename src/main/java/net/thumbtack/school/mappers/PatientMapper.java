package net.thumbtack.school.mappers;

import net.thumbtack.school.models.Patient;
import net.thumbtack.school.models.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PatientMapper {

    @Insert("INSERT INTO `patients` (user_id, email, address, phone) VALUES (#{user.id}, #{patient.email}, #{patient.address}, #{patient.phone})")
    @Options(useGeneratedKeys = true, keyProperty = "patient.id")
    Integer insert(@Param("patient") Patient patient, @Param("user") User user);

    @Select("SELECT id_patient, user_id, email, address, phone FROM patients WHERE id_patient = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id_patient"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT))
    })
    Patient getById(int id);

    @Select("SELECT id_patient, user_id, email, address, phone FROM patients WHERE user_id = #{id_user}")
    @Results(value = {
            @Result(property = "id", column = "id_patient"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT))
    })
    Patient getByIdUser(int id_user);

    @Delete("DELETE FROM patients")
    void deleteAll();

    @Update("UPDATE patients SET email = #{patient.email}, address = #{patient.address}, phone = #{patient.phone}  WHERE user_id = #{user.id}")
    void update(@Param("patient") Patient patient, @Param("user") User user);

    @Select("SELECT id_patient, user_id, email, address, phone FROM patients")
    @Results(value = {
            @Result(property = "id", column = "id_patient"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT))
    })
    List<Patient> getAll();
}