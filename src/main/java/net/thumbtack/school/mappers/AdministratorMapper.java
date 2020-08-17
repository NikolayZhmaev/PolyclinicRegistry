package net.thumbtack.school.mappers;

import net.thumbtack.school.models.Administrator;
import net.thumbtack.school.models.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AdministratorMapper {

    @Insert("INSERT INTO `administrators` (user_id, position) VALUES (#{user.id}, #{administrator.position})")
    @Options(useGeneratedKeys = true, keyProperty = "administrator.id")
    Integer insert(@Param("administrator") Administrator administrator, @Param("user") User user);

    @Select("SELECT id_administrator, position, user_id FROM administrators WHERE user_id = #{id_user}")
    @Results(value = {
            @Result(property = "id", column = "id_administrator"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT))
    })
    Administrator getByIdUser(int id_user);

    @Delete("DELETE FROM administrators")
    void deleteAll();

    @Update("UPDATE administrators SET position = #{administrator.position} WHERE user_id = #{user.id}")
    void update(@Param("administrator") Administrator administrator, @Param("user") User user);

    @Select("SELECT id_administrator, position, user_id FROM administrators")
    @Results(value = {
            @Result(property = "id", column = "id_administrator"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.mappers.UserMapper.getById", fetchType = FetchType.DEFAULT))
    })
    List<Administrator> getAll();
}