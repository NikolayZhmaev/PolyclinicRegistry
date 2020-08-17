package net.thumbtack.school.mappers;

import net.thumbtack.school.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert("INSERT INTO `users` (category, firstName, lastName, patronymic, login, password) VALUES (#{category}, #{firstName}, #{lastName}, #{patronymic}, #{login}, #{password})")
    @Options(useGeneratedKeys = true)
    Integer insert(User user);

    @Select("SELECT * FROM users WHERE login = #{login}")
    User getByLogin(String login);

    @Select("SELECT * FROM users WHERE id_user = #{id}")
    User getById(int id);

    @Delete("DELETE FROM users")
    void deleteAll();

    @Delete("DELETE FROM users WHERE id_user = #{id}")
    void delete(User user);

    @Select("SELECT id_user, category, firstName, lastName, patronymic, login, password FROM users")
    List<User> getAll();

    @Update("UPDATE users SET firstName = #{firstName}, lastName = #{lastName}, patronymic = #{patronymic}, password = #{password} WHERE id_user = #{id}")
    void update(User user);
}