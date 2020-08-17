package net.thumbtack.school.daoimpl;

import net.thumbtack.school.mappers.*;
import org.apache.ibatis.session.SqlSession;
import net.thumbtack.school.utils.MyBatisUtils;

public class DaoImplBase {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }

    protected AdministratorMapper getAdministratorMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(AdministratorMapper.class);
    }

    protected PatientMapper getPatientMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(PatientMapper.class);
    }

    protected DoctorMapper getDoctorMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(DoctorMapper.class);
    }

    protected ScheduleMapper getScheduleMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ScheduleMapper.class);
    }

    protected AppointmentMapper getAppointmentMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(AppointmentMapper.class);
    }

    protected TicketMapper getTicketMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(TicketMapper.class);
    }

    protected RoomAndSpecialtiesMapper getRoomAndSpecialtiesMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(RoomAndSpecialtiesMapper.class);
    }
}