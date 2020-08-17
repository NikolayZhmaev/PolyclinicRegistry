package net.thumbtack.school;

import net.thumbtack.school.dao.*;
import net.thumbtack.school.daoimpl.*;
import net.thumbtack.school.models.ScheduleItem;
import net.thumbtack.school.models.User;
import org.junit.Before;
import org.junit.BeforeClass;
import net.thumbtack.school.serviceClasses.UserCategory;
import net.thumbtack.school.utils.MyBatisUtils;

import static org.junit.Assert.assertNotEquals;

public class TestDataBase {

    protected UserDao userDao = new UserDaoImpl();
    protected AdministratorDao administratorDao = new AdministratorDaoImpl();
    protected PatientDao patientDao = new PatientDaoImpl();
    protected DoctorDao doctorDao = new DoctorDaoImpl();
    protected ScheduleDao scheduleDao = new ScheduleDaoImpl();
    protected AppointmentDao appointmentDao = new AppointmentDaoImpl();
    protected RoomAndSpecialtiesDao roomAndSpecialtiesDao = new RoomAndSpecialtiesDaoImpl();
    protected TicketDao ticketDao = new TicketDaoImpl();

    private static boolean setUpIsDone = false;

    @Before
    public void clearDatabase() {
        roomAndSpecialtiesDao.deleteAllRooms();
        userDao.deleteAll();
        administratorDao.deleteAll();
        patientDao.deleteAll();
        doctorDao.deleteAll();
        scheduleDao.deleteAll();
        ticketDao.deleteAll();
    }

    @BeforeClass()
    public static void setUp() {
        if (!setUpIsDone) {
            boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
            if (!initSqlSessionFactory) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
        }
    }

    protected User insertUser(UserCategory category, String firstName, String lastName, String patronymic, String login, String password) {
        User user = new User(category, firstName, lastName, patronymic, login, password);
        userDao.insert(user);
        assertNotEquals(0, user.getId());
        return user;
    }
}