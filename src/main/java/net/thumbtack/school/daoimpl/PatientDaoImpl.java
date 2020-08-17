package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.PatientDao;
import net.thumbtack.school.models.Patient;
import net.thumbtack.school.models.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PatientDaoImpl extends DaoImplBase implements PatientDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientDaoImpl.class);

    @Override
    public Patient insert(Patient patient) {
        LOGGER.debug("DAO insert Patient {}", patient);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(patient.getUser());
                getPatientMapper(sqlSession).insert(patient, patient.getUser());

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Patient {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return patient;
    }

    @Override
    public Patient getByLogin(String login) {
        LOGGER.debug("DAO get Patient by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            try {
                User user = getUserMapper(sqlSession).getByLogin(login);
                if (user == null) {
                    return null;
                } else
                    return getPatientMapper(sqlSession).getByIdUser(user.getId());

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get Patient by login {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public Patient update(Patient patient) {
        LOGGER.debug("DAO update Patient {}", patient);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(patient.getUser());
                getPatientMapper(sqlSession).update(patient, patient.getUser());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update Patient {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return patient;
    }

    @Override
    public List<Patient> getAll() {
        LOGGER.debug("DAO get all Patients");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getPatientMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Patients {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void batchInsert(List<Patient> patients) {
        LOGGER.debug("DAO insert Patients {}", patients);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Patient patient : patients) {
                    getUserMapper(sqlSession).insert(patient.getUser());
                    getPatientMapper(sqlSession).insert(patient, patient.getUser());
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Patients {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Patient patient) {
        LOGGER.debug("DAO delete Patient {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).delete(patient.getUser());
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Patient {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Patients {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getPatientMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Patients {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}