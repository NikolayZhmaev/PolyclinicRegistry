package net.thumbtack.school.mappers;

import net.thumbtack.school.models.Patient;
import net.thumbtack.school.models.ScheduleItem;
import net.thumbtack.school.models.Ticket;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface TicketMapper {

    @Insert("INSERT INTO `tickets` (patient_id, appointment_id) VALUES (#{ticket.patient.id}, #{ticket.scheduleItem.id})")
    @Options(useGeneratedKeys = true, keyProperty = "ticket.id")
    Integer insert(@Param("ticket") Ticket ticket);

    @Select("SELECT id_ticket, patient_id, appointment_id FROM tickets WHERE id_ticket = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id_ticket"),
            @Result(property = "patient", column = "patient_id", javaType = Patient.class,
                    one = @One(select = "net.thumbtack.school.mappers.PatientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "scheduleItem", column = "appointment_id", javaType = ScheduleItem.class,
                    one = @One(select = "net.thumbtack.school.mappers.AppointmentMapper.getById", fetchType = FetchType.LAZY))
    })
    Ticket getById(int id);

    @Select("SELECT id_ticket, patient_id, appointment_id FROM tickets WHERE patient_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id_ticket"),
            @Result(property = "patient", column = "patient_id", javaType = Patient.class,
                    one = @One(select = "net.thumbtack.school.mappers.PatientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "scheduleItem", column = "appointment_id", javaType = ScheduleItem.class,
                    one = @One(select = "net.thumbtack.school.mappers.AppointmentMapper.getById", fetchType = FetchType.LAZY))
    })
    List<Ticket> getByIdPatient(int id);

    @Update("UPDATE tickets SET patient_id = #{ticket.patient.id}, appointment_id = #{ticket.scheduleItem.id} WHERE id_ticket = #{ticket.id}")
    void update(@Param("ticket") Ticket ticket);

    @Select("SELECT id_ticket, patient_id, appointment_id FROM tickets")
    @Results(value = {
            @Result(property = "id", column = "id_ticket"),
            @Result(property = "patient", column = "patient_id", javaType = Patient.class,
                    one = @One(select = "net.thumbtack.school.mappers.PatientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "scheduleItem", column = "appointment_id", javaType = ScheduleItem.class,
                    one = @One(select = "net.thumbtack.school.mappers.AppointmentMapper.getById", fetchType = FetchType.LAZY))
    })
    List<Ticket> getAll();

    @Delete("DELETE FROM tickets WHERE id_ticket = #{id}")
    void delete(Ticket ticket);

    @Delete("DELETE FROM tickets")
    void deleteAll();
}