package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.RoomAndSpecialtiesDao;
import net.thumbtack.school.models.Room;
import net.thumbtack.school.models.Speciality;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoomAndSpecialtiesDaoImpl extends DaoImplBase implements RoomAndSpecialtiesDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomAndSpecialtiesDaoImpl.class);

    @Override
    public List<Room> initializationRooms(List<Room> rooms) {
        LOGGER.debug("DAO insert Rooms {}", rooms);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Room room : rooms) {
                    getRoomAndSpecialtiesMapper(sqlSession).insertRoom(room);
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Rooms {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return rooms;
    }

    @Override
    public void initializationSpeciality(List<String> specialities) {
        LOGGER.debug("DAO insert Specialities {}", specialities);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (String speciality : specialities) {
                    getRoomAndSpecialtiesMapper(sqlSession).insertSpeciality(speciality);
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Specialities {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public List<Speciality> getAllSpecialties() {
        LOGGER.debug("DAO get all Specialties");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getRoomAndSpecialtiesMapper(sqlSession).getAllSpecialties();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Specialties {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public String getNameSpecialityById(int id) {
        LOGGER.debug("DAO get name of Speciality by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getRoomAndSpecialtiesMapper(sqlSession).getNameSpecialityById(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get name of Speciality by id {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public Room setStatusRoom(Room room) {
        LOGGER.debug("DAO set status of the Room {}", room);
        try (SqlSession sqlSession = getSession()) {
            try {
                getRoomAndSpecialtiesMapper(sqlSession).setStateRoom(room);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't set status of the Room {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return room;
    }

    @Override
    public List<Room> getAllFreeRooms() {
        LOGGER.debug("DAO get all free Rooms");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getRoomAndSpecialtiesMapper(sqlSession).getAllFreeRooms();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all free Rooms {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Room> getAllRooms() {
        LOGGER.debug("DAO get all Rooms");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getRoomAndSpecialtiesMapper(sqlSession).getAllRooms();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Rooms {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void deleteAllRooms() {
        LOGGER.debug("DAO get all Rooms");
        try (SqlSession sqlSession = getSession()) {
            try {
                getRoomAndSpecialtiesMapper(sqlSession).deleteAllRooms();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Rooms {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public List<Speciality> getDoctorsSpeciality(String speciality) {
        LOGGER.debug("DAO get all Doctors of the specialty {}", speciality);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getRoomAndSpecialtiesMapper(sqlSession).getSpeciality(speciality);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Doctors of the specialty {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void deleteAllSpecialties() {
        LOGGER.debug("DAO get all Specialties");
        try (SqlSession sqlSession = getSession()) {
            try {
                getRoomAndSpecialtiesMapper(sqlSession).deleteAllSpecialties();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Specialties {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}