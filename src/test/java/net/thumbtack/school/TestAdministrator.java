package net.thumbtack.school;

import net.thumbtack.school.models.Administrator;
import net.thumbtack.school.models.User;
import org.junit.Test;
import net.thumbtack.school.serviceClasses.UserCategory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestAdministrator extends TestDataBase {

    @Test
    public void testInsertAdministrator() {
        try {
            User user = new User(UserCategory.ADMINISTRATOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Administrator administrator = new Administrator("бухгалтер", user);
            Administrator administratorFromDB = administratorDao.insert(administrator);
            assertNotEquals(0, administratorFromDB.getId());
            assertNotEquals(0, user.getId());
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAdministrator() {
        try {
            User user = new User(UserCategory.ADMINISTRATOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Administrator administrator = new Administrator("бухгалтер", user);
            Administrator insertAdministrator = administratorDao.insert(administrator);
            Administrator foundAdministrator = administratorDao.getByLogin("Ivanov");
            assertEquals(foundAdministrator, insertAdministrator);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdateAdministrator() {
        User user = new User(UserCategory.ADMINISTRATOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        Administrator administrator = new Administrator("бухгалтер", user);
        administratorDao.insert(administrator);
        administrator.setPosition("старший бухгалтер");
        administrator.getUser().setPassword("new2222");
        administratorDao.update(administrator);
        Administrator foundAdministrator = administratorDao.getByLogin("Ivanov");
        assertEquals(foundAdministrator.getPosition(), "старший бухгалтер");
        assertEquals(foundAdministrator.getUser().getPassword(), "new2222");
    }

    @Test
    public void testDeleteAdministrator() {
        try {
            User user = new User(UserCategory.ADMINISTRATOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Administrator administrator = new Administrator("бухгалтер", user);
            administratorDao.insert(administrator);
            Administrator administratorFromDB = administratorDao.getByLogin("Ivanov");
            assertEquals(administrator, administratorFromDB);
            administratorDao.delete(administrator);
            assertNull(administratorDao.getByLogin("Ivanov"));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllAdministrators() {
        User userIvanov = new User(UserCategory.ADMINISTRATOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        Administrator administratorIvanov = new Administrator("бухгалтер", userIvanov);
        User userPetrov = new User(UserCategory.ADMINISTRATOR, "Петр", "Петров", "Иванович", "Petrov", "I2222");
        Administrator administratorPetrov = new Administrator("бухгалтер", userPetrov);
        User userSidorov = new User(UserCategory.ADMINISTRATOR, "Николай", "Сидоров", "Иванович", "Sidorov", "I2222");
        Administrator administratorSidorov = new Administrator("бухгалтер", userSidorov);

        administratorDao.insert(administratorIvanov);
        administratorDao.insert(administratorPetrov);
        administratorDao.insert(administratorSidorov);

        List<Administrator> administrators = new ArrayList<>();
        administrators.add(administratorIvanov);
        administrators.add(administratorPetrov);
        administrators.add(administratorSidorov);

        List<Administrator> administratorsFromDB = administratorDao.getAll();
        assertEquals(administratorsFromDB, administrators);
    }

    @Test
    public void testBatchInsertAndGetAllAdministrators() {
        User userIvanov = new User(UserCategory.ADMINISTRATOR, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        Administrator administratorIvanov = new Administrator("бухгалтер", userIvanov);
        User userPetrov = new User(UserCategory.ADMINISTRATOR, "Петр", "Петров", "Иванович", "Petrov", "I2222");
        Administrator administratorPetrov = new Administrator("бухгалтер", userPetrov);
        User userSidorov = new User(UserCategory.ADMINISTRATOR, "Николай", "Сидоров", "Иванович", "Sidorov", "I2222");
        Administrator administratorSidorov = new Administrator("бухгалтер", userSidorov);

        List<Administrator> administrators = new ArrayList<>();
        administrators.add(administratorIvanov);
        administrators.add(administratorPetrov);
        administrators.add(administratorSidorov);

        administratorDao.batchInsert(administrators);

        List<Administrator> administratorsFromDB = administratorDao.getAll();
        assertEquals(administratorsFromDB, administrators);
    }
}