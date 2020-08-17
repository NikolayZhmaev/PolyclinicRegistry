package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.AdministratorDao;
import net.thumbtack.school.models.Administrator;
import net.thumbtack.school.models.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdministratorDaoImpl extends DaoImplBase implements AdministratorDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorDaoImpl.class);

    @Override
    public Administrator insert(Administrator administrator) {
        LOGGER.debug("DAO insert Administrator {}", administrator);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(administrator.getUser());
                getAdministratorMapper(sqlSession).insert(administrator, administrator.getUser());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Administrator {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return administrator;
    }

    @Override
    public Administrator getByLogin(String login) {
        LOGGER.debug("DAO get Administrator by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            try {
                User user = getUserMapper(sqlSession).getByLogin(login);
                if (user == null) {
                    return null;
                } else
                    return getAdministratorMapper(sqlSession).getByIdUser(user.getId());

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get Administrator by login {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public Administrator update(Administrator administrator) {
        LOGGER.debug("DAO update Administrator {}", administrator);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(administrator.getUser());
                getAdministratorMapper(sqlSession).update(administrator, administrator.getUser());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update Administrator {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return administrator;
    }

    @Override
    public List<Administrator> getAll() {
        LOGGER.debug("DAO get all Administrators");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getAdministratorMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Administrators {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void batchInsert(List<Administrator> administrators) {
        LOGGER.debug("DAO insert Administrators {}", administrators);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Administrator administrator : administrators) {
                    getUserMapper(sqlSession).insert(administrator.getUser());
                    getAdministratorMapper(sqlSession).insert(administrator, administrator.getUser());
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Administrators {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Administrator administrator) {
        LOGGER.debug("DAO delete Administrator {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).delete(administrator.getUser());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Administrator {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Administrators {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getAdministratorMapper(sqlSession).deleteAll();

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Administrators {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}