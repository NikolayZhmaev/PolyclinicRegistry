package net.thumbtack.school.daoimpl;

import net.thumbtack.school.dao.TicketDao;
import net.thumbtack.school.models.Ticket;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TicketDaoImpl extends DaoImplBase implements TicketDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImpl.class);

    @Override
    public Ticket insert(Ticket ticket) {
        LOGGER.debug("DAO insert Ticket {}", ticket);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTicketMapper(sqlSession).insert(ticket);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Ticket {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return ticket;
    }

    @Override
    public Ticket getById(int id) {
        LOGGER.debug("DAO get Ticket by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getTicketMapper(sqlSession).getById(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get Ticket {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Ticket> getByIdPatient(int id) {
        LOGGER.debug("DAO get Ticket by id Patient {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getTicketMapper(sqlSession).getByIdPatient(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get Ticket by id Patient {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        LOGGER.debug("DAO update Ticket {}", ticket);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTicketMapper(sqlSession).update(ticket);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update Ticket {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        LOGGER.debug("DAO get all Tickets");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getTicketMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't get all Tickets {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void batchInsert(List<Ticket> tickets) {
        LOGGER.debug("DAO insert Tickets {}", tickets);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Ticket ticket : tickets) {
                    getTicketMapper(sqlSession).insert(ticket);
                }
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Tickets {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        LOGGER.debug("DAO delete Ticket {}", ticket);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTicketMapper(sqlSession).delete(ticket);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Ticket {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Tickets {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getTicketMapper(sqlSession).deleteAll();

            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Tickets {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}