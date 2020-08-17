package net.thumbtack.school;

import net.thumbtack.school.models.Patient;
import net.thumbtack.school.models.User;
import org.junit.Test;
import net.thumbtack.school.serviceClasses.UserCategory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPatient extends TestDataBase {

    @Test
    public void testInsertPatient() {
        try {
            User user = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Patient patient = new Patient("email", "address", "13-13-13", user);
            Patient patientFromDB = patientDao.insert(patient);
            assertNotEquals(0, patientFromDB.getId());
            assertNotEquals(0, user.getId());
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetPatient() {
        try {
            User user = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Patient patient = new Patient("email", "address", "13-13-13", user);
            Patient insertPatient = patientDao.insert(patient);
            Patient foundPatient = patientDao.getByLogin("Ivanov");
            assertEquals(foundPatient, insertPatient);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdatePatient() {
        User user = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        Patient patient = new Patient("email", "address", "13-13-13", user);
        patientDao.insert(patient);
        patient.setAddress("new address");
        patient.getUser().setPassword("new2222");
        patient.setPhone("new");
        patientDao.update(patient);
        Patient foundPatient = patientDao.getByLogin("Ivanov");
        assertEquals(foundPatient.getAddress(), "new address");
        assertEquals(foundPatient.getUser().getPassword(), "new2222");
        assertEquals(foundPatient.getPhone(), "new");
    }

    @Test
    public void testDeletePatient() {
        try {
            User user = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
            Patient patient = new Patient("email", "address", "13-13-13", user);
            patientDao.insert(patient);
            Patient patientFromDB = patientDao.getByLogin("Ivanov");
            assertEquals(patient, patientFromDB);
            patientDao.delete(patient);
            assertNull(patientDao.getByLogin("Ivanov"));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllPatients() {
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        Patient patientIvanov = new Patient("email", "address", "13-13-13", userIvanov);
        User userPetrov = new User(UserCategory.PATIENT, "Петр", "Петров", "Иванович", "Petrov", "I2222");
        Patient patientPetrov = new Patient("email", "address", "13-13-13", userPetrov);
        User userSidorov = new User(UserCategory.PATIENT, "Николай", "Сидоров", "Иванович", "Sidorov", "I2222");
        Patient patientSidorov = new Patient("email", "address", "13-13-13", userSidorov);

        patientDao.insert(patientIvanov);
        patientDao.insert(patientPetrov);
        patientDao.insert(patientSidorov);

        List<Patient> patients = new ArrayList<>();
        patients.add(patientIvanov);
        patients.add(patientPetrov);
        patients.add(patientSidorov);

        List<Patient> patientsFromDB = patientDao.getAll();
        assertEquals(patientsFromDB, patients);
    }

    @Test
    public void testBatchInsertAndGetAllAdministrators() {
        User userIvanov = new User(UserCategory.PATIENT, "Иван", "Иванов", "Иванович", "Ivanov", "I2222");
        Patient patientIvanov = new Patient("email", "address", "13-13-13", userIvanov);
        User userPetrov = new User(UserCategory.PATIENT, "Петр", "Петров", "Иванович", "Petrov", "I2222");
        Patient patientPetrov = new Patient("email", "address", "13-13-13", userPetrov);
        User userSidorov = new User(UserCategory.PATIENT, "Николай", "Сидоров", "Иванович", "Sidorov", "I2222");
        Patient patientSidorov = new Patient("email", "address", "13-13-13", userSidorov);

        List<Patient> patients = new ArrayList<>();
        patients.add(patientIvanov);
        patients.add(patientPetrov);
        patients.add(patientSidorov);

        patientDao.batchInsert(patients);

        List<Patient> patientsFromDB = patientDao.getAll();
        assertEquals(patientsFromDB, patients);
    }
}