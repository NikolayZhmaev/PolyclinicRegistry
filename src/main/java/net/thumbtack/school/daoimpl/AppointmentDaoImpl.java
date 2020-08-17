package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.AppointmentDao;
import net.thumbtack.school.models.Schedule;
import net.thumbtack.school.models.ScheduleItem;
import net.thumbtack.school.serviceClasses.State;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AppointmentDaoImpl extends DaoImplBase implements AppointmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDaoImpl.class);

    @Override
    public ScheduleItem insert(ScheduleItem scheduleItem) {
        LOGGER.debug("DAO insert ScheduleItem {}", scheduleItem);
        try (SqlSession sqlSession = getSession()) {
            try {
                getAppointmentMapper(sqlSession).insert(scheduleItem, scheduleItem.getSchedule());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert ScheduleItem {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return scheduleItem;
    }

    @Override
    public ScheduleItem getById(int id) {
        LOGGER.debug("DAO get ScheduleItem by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getAppointmentMapper(sqlSession).getById(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get ScheduleItem {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public List<ScheduleItem> getByIdSchedule(Schedule schedule) {
        LOGGER.debug("DAO get all ScheduleItems by id Schedule {}", schedule.getId());
        try (SqlSession sqlSession = getSession()) {
            try {
                return getAppointmentMapper(sqlSession).getAllByIdSchedule(schedule);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all ScheduleItems by id Schedule {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public List<ScheduleItem> getByIdScheduleAndState(Schedule schedule, State state) {
        LOGGER.debug("DAO get all ScheduleItems by id Schedule, and State {}", schedule.getId(), state);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getAppointmentMapper(sqlSession).getAllByIdScheduleAndState(schedule, state);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all ScheduleItems by id Schedule {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public ScheduleItem update(ScheduleItem scheduleItem) {
        LOGGER.debug("DAO update ScheduleItem {}", scheduleItem);
        try (SqlSession sqlSession = getSession()) {
            try {
                getAppointmentMapper(sqlSession).update(scheduleItem, scheduleItem.getSchedule());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update ScheduleItem {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return scheduleItem;
    }

    @Override
    public List<ScheduleItem> getAll() {
        LOGGER.debug("DAO get all ScheduleItems");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getAppointmentMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all ScheduleItems {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void batchInsert(List<ScheduleItem> scheduleItems) {
        LOGGER.debug("DAO insert ScheduleItems {}", scheduleItems);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (ScheduleItem scheduleItem : scheduleItems) {
                    getAppointmentMapper(sqlSession).insert(scheduleItem, scheduleItem.getSchedule());
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert ScheduleItems {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(ScheduleItem scheduleItem) {
        LOGGER.debug("DAO delete ScheduleItem {}", scheduleItem);
        try (SqlSession sqlSession = getSession()) {
            try {
                getAppointmentMapper(sqlSession).delete(scheduleItem);

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete ScheduleItem {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all ScheduleItems {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getAppointmentMapper(sqlSession).deleteAll();

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all ScheduleItems {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}