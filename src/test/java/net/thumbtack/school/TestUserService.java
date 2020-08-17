package net.thumbtack.school;

import net.thumbtack.school.errors.ServiceException;

import net.thumbtack.school.models.User;
import org.junit.Test;
import net.thumbtack.school.serviceClasses.UserCategory;
import net.thumbtack.school.serviceClasses.UserService;

import static org.junit.Assert.*;

public class TestUserService extends TestDataBase {

    @Test
    public void testInsertUser() {
        UserService userService = new UserService(12, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", null, "Ivanov", "I2222");
        try {
            userService.userRegister(userIvanov);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        assertNotEquals(userIvanov.getId(), 0);
    }

    @Test
    public void testIncorrectFirstName() {
        UserService userService = new UserService(12, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "ИVан", "Иванов", "Иванович", "Ivanov", "I2222");
        try {
            userService.userRegister(userIvanov);
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "First name is not valid");
        }
    }

    @Test
    public void testIncorrectLogin() {
        UserService userService = new UserService(15, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван да Марья", "Иванов", "Иванович", "IvanovIvanIvanovich", "I2222");
        try {
            userService.userRegister(userIvanov);
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "Login is not valid");
        }
    }

    @Test
    public void testIncorrectPassword() {
        UserService userService = new UserService(15, 8, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван да Марья", "Иванов", "Иванович", "Ivanov", "I2222");
        try {
            userService.userRegister(userIvanov);
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "Password is not valid");
        }
    }

    @Test
    public void testRegisterBusyLogin() throws ServiceException {
        UserService userService = new UserService(15, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        User userNewIvanov = new User(UserCategory.PATIENT, "Сергей", "Иванов", null, "Ivanov", "I2222");
        userService.userRegister(userIvanov);
        try {
            userService.userRegister(userNewIvanov);
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "The login was");
        }
    }

    @Test
    public void testGetUserByLogin() throws ServiceException {
        UserService userService = new UserService(15, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        userService.userRegister(userIvanov);
        User userFromDB = userService.userFindByLogin("Ivanov");
        assertEquals(userFromDB, userIvanov);
    }

    @Test
    public void testGetUserByExistLogin() throws ServiceException {
        UserService userService = new UserService(15, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        userService.userRegister(userIvanov);
        try {
            userService.userFindByLogin("Pivanov");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "User not found");
        }
    }

    @Test
    public void testGetUserById() throws ServiceException {
        UserService userService = new UserService(15, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        userService.userRegister(userIvanov);
        User userFromDB = userService.userFindById(userIvanov.getId());
        assertEquals(userFromDB, userIvanov);
    }

    @Test(expected = ServiceException.class)
    public void testGetUserByExistId() throws ServiceException {
        UserService userService = new UserService(15, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        userService.userRegister(userIvanov);
        User userFromDB = userService.userFindById(0);
    }

    @Test
    public void testUpdateUser() throws ServiceException {
        UserService userService = new UserService(15, 1, userDao);
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        userService.userRegister(userIvanov);
        userIvanov.setFirstName("Иван да Марья");
        User userFromDB = userService.userUpdate(userIvanov);
        assertEquals(userFromDB, userIvanov);
    }
}
