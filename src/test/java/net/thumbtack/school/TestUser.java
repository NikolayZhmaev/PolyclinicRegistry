package net.thumbtack.school;

import net.thumbtack.school.models.User;
import org.junit.Test;
import net.thumbtack.school.serviceClasses.UserCategory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestUser extends TestDataBase {

    @Test
    public void testInsertUser() {
        try {
            User user = insertUser(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            assertNotEquals(0, user.getId());
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertUserAndGetUserByLogin() {
        try {
            User user = insertUser(UserCategory.PATIENT, "Петр", "Петров", "Петрович", "Petrov", "I2222");
            assertNotEquals(0, user.getId());
            User userFromDB = userDao.getByLogin("Petrov");
            assertEquals(user, userFromDB);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertUserAndGetUserById() {
        try {
            User user = insertUser(UserCategory.PATIENT, "Петр", "Петров", "Петрович", "Petrov", "I2222");
            assertNotEquals(0, user.getId());
            User userFromDB = userDao.getById(user.getId());
            assertEquals(user, userFromDB);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteUser() {
        try {
            User user = insertUser(UserCategory.PATIENT, "Петр", "Петров", "Петрович", "Petrov", "I2222");
            User userFromDB = userDao.getByLogin("Petrov");
            assertEquals(user, userFromDB);
            userDao.delete(user);
            assertNull(userDao.getByLogin("Petrov"));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAll() {
        try {
            User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            User userPetrov = new User(UserCategory.PATIENT, "Петр", "Петров", "Петрович", "Petrov", "P2222");
            User userSidorov = new User(UserCategory.PATIENT, "Алексей", "Сидоров", "Алексеевич", "Sidorov", "S2222");

            List<User> users = new ArrayList<>();
            users.add(userIvanov);
            users.add(userPetrov);
            users.add(userSidorov);
            userDao.batchInsert(users);
            List<User> usersFromDB = userDao.getAll();
            assertEquals(usersFromDB, users);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdateUser() {
        User user = insertUser(UserCategory.PATIENT, "Петр", "Петров", "Петрович", "Petrov", "I2222");
        User userFromDB = userDao.getByLogin("Petrov");
        assertEquals(user, userFromDB);
        user.setFirstName("Иван");
        userDao.update(user);
        userFromDB = userDao.getByLogin("Petrov");
        assertEquals(userFromDB, user);
    }
}