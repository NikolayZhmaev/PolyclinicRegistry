package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.ScheduleDao;
import net.thumbtack.school.models.Schedule;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ScheduleDaoImpl extends DaoImplBase implements ScheduleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleDaoImpl.class);

    @Override
    public Schedule insert(Schedule schedule) {
        LOGGER.debug("DAO insert Schedule {}", schedule);
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).insert(schedule, schedule.getDoctor());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Schedule {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return schedule;
    }

    @Override
    public Schedule getBySpeciality(String speciality) {
        return null;
    }

    @Override
    public Schedule getById(int id) {
        LOGGER.debug("DAO get Schedule by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getScheduleMapper(sqlSession).getById(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get Schedule {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Schedule> getByIdDoctor(int id) {
        LOGGER.debug("DAO get all Schedules by id doctor {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getScheduleMapper(sqlSession).getAllByDoctorId(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Schedules by id doctor {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public Schedule update(Schedule schedule) {
        LOGGER.debug("DAO update Schedule {}", schedule);
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).update(schedule, schedule.getDoctor());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update Schedule {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return schedule;
    }

    @Override
    public List<Schedule> getAll() {
        LOGGER.debug("DAO get all Schedules");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getScheduleMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Schedules {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void batchInsert(List<Schedule> schedules) {
        LOGGER.debug("DAO insert Schedules {}", schedules);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Schedule schedule : schedules) {
                    getScheduleMapper(sqlSession).insert(schedule, schedule.getDoctor());
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Schedules {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Schedule schedule) {
        LOGGER.debug("DAO delete Schedule {}", schedule);
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).delete(schedule);

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Schedule {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Schedules {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).deleteAll();

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Schedules {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}