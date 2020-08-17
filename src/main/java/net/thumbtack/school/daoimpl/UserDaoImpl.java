package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.UserDao;
import net.thumbtack.school.models.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class UserDaoImpl extends DaoImplBase implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User insert(User user) {
        LOGGER.debug("DAO insert user {}", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert user {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return user;
    }

    @Override
    public User getByLogin(String login) {
        LOGGER.debug("DAO get user by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getByLogin(login);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get user {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public User getById(int id) {
        LOGGER.debug("DAO get user by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getById(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get user {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Users {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteAll();

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Users {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public User update(User user) {
        LOGGER.debug("DAO update User {}", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update User {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        LOGGER.debug("DAO get all Users");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Users {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void batchInsert(List<User> users) {
        LOGGER.debug("DAO insert Users {}", users);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (User user : users) {
                    getUserMapper(sqlSession).insert(user);
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Users {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(User user) {
        LOGGER.debug("DAO delete User {}", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).delete(user);

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete User {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}