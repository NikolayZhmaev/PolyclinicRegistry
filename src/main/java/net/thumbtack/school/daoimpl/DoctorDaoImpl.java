package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.DoctorDao;
import net.thumbtack.school.models.Doctor;
import net.thumbtack.school.models.Schedule;
import net.thumbtack.school.models.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DoctorDaoImpl extends DaoImplBase implements DoctorDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDaoImpl.class);

    @Override
    public Doctor insert(Doctor doctor) {
        LOGGER.debug("DAO insert Doctor {}", doctor);
        try (SqlSession sqlSession = getSession()) {
            try {
                getRoomAndSpecialtiesMapper(sqlSession).setStateRoom(doctor.getRoom());
                getUserMapper(sqlSession).insert(doctor.getUser());
                getDoctorMapper(sqlSession).insert(doctor, doctor.getUser(), doctor.getRoom(), doctor.getSpeciality());
                for (Schedule schedule : doctor.getSchedules()) {
                    schedule.setDoctor(doctor);
                    getScheduleMapper(sqlSession).insert(schedule, schedule.getDoctor());
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Doctor {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return doctor;
    }

    @Override
    public Doctor getByLogin(String login) {
        LOGGER.debug("DAO get Doctor by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            try {
                User user = getUserMapper(sqlSession).getByLogin(login);
                if (user == null) {
                    return null;
                } else
                    return getDoctorMapper(sqlSession).getByIdUser(user.getId());

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get Doctor by login {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public Doctor update(Doctor doctor) {
        LOGGER.debug("DAO update Doctor {}", doctor);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(doctor.getUser());
                getDoctorMapper(sqlSession).update(doctor, doctor.getUser(), doctor.getRoom(), doctor.getSpeciality());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update Doctor {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return doctor;
    }

    @Override
    public List<Doctor> getAll() {
        LOGGER.debug("DAO get all Doctors");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getDoctorMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Doctors {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void batchInsert(List<Doctor> doctors) {
        LOGGER.debug("DAO insert Doctors {}", doctors);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Doctor doctor : doctors) {
                    getRoomAndSpecialtiesMapper(sqlSession).setStateRoom(doctor.getRoom());
                    getUserMapper(sqlSession).insert(doctor.getUser());
                    getDoctorMapper(sqlSession).insert(doctor, doctor.getUser(), doctor.getRoom(), doctor.getSpeciality());
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Doctors {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Doctor doctor) {
        LOGGER.debug("DAO delete Doctor {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).delete(doctor.getUser());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Doctor {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Doctors {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getDoctorMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Doctors {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}