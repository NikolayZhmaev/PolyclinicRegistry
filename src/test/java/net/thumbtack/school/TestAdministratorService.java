package net.thumbtack.school;

import com.google.gson.Gson;
import net.thumbtack.school.dto.request.RegisterAdministratorDtoRequest;
import net.thumbtack.school.dto.response.ErrorResponse;
import net.thumbtack.school.dto.response.RegisterAdministratorDtoResponse;
import net.thumbtack.school.errors.ServiceErrorCode;
import net.thumbtack.school.serviceClasses.AdministratorService;
import net.thumbtack.school.serviceClasses.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAdministratorService extends TestDataBase {


    UserService userService = new UserService(12, 1, userDao);
    AdministratorService administratorService = new AdministratorService(userService, administratorDao);


    Gson gson = new Gson();

    public String requerstString(RegisterAdministratorDtoRequest request) {
        return gson.toJson(request);
    }

    public RegisterAdministratorDtoResponse responseRegister(String response) {
        RegisterAdministratorDtoResponse administratorDtoResponse = gson.fromJson(response, RegisterAdministratorDtoResponse.class);
        return administratorDtoResponse;
    }

    private ErrorResponse responseErrorRegister(String response) {
        ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
        return errorResponse;
    }

    @Test
    public void testInsertAdministrator() {
        RegisterAdministratorDtoRequest request = new RegisterAdministratorDtoRequest("Иван", "Иванов", "Иванович", "бухгалтер", "Ivanov", "I2222");
        String response = administratorService.administratorRegister(requerstString(request));
        RegisterAdministratorDtoResponse rAdm = responseRegister(response);
        assertNotEquals(rAdm.getId(), 0);
    }

    @Test
    public void testInsertAdministratorWithoutPatronymic() {
        RegisterAdministratorDtoRequest request = new RegisterAdministratorDtoRequest("Иван", "Иванов", "бухгалтер", "Ivanov", "I2222");
        String response = administratorService.administratorRegister(requerstString(request));
        RegisterAdministratorDtoResponse rAdm = responseRegister(response);
        assertNotEquals(rAdm.getId(), 0);
        assertEquals(rAdm.getPatronymic(), null);
        assertTrue(response.contains("\"firstName\":\"Иван\",\"lastName\":\"Иванов\",\"position\":\"бухгалтер\"}"));
    }

    @Test
    public void testInsertIncorrectFirstName() {
        RegisterAdministratorDtoRequest request = new RegisterAdministratorDtoRequest("ИVван", "Иванов", "Иванович", "бухгалтер", "Ivanov", "I2222");
        String response = administratorService.administratorRegister(requerstString(request));
        ErrorResponse errorResponse = responseErrorRegister(response);
        assertEquals(errorResponse.getMessage(), "First name is not valid");
        assertEquals(errorResponse.getErrorCode(), ServiceErrorCode.USER_WRONG_FIRSTNAME);
        assertEquals(errorResponse.getField(), "firstName");
    }

    @Test
    public void testInsertIncorrectLastName() {
        RegisterAdministratorDtoRequest request = new RegisterAdministratorDtoRequest("Иван", "ИваноV", "Иванович", "бухгалтер", "Ivanov", "I2222");
        String response = administratorService.administratorRegister(requerstString(request));
        ErrorResponse errorResponse = responseErrorRegister(response);
        assertEquals(errorResponse.getMessage(), "Last name is not valid");
        assertEquals(errorResponse.getErrorCode(), ServiceErrorCode.USER_WRONG_LASTNAME);
        assertEquals(errorResponse.getField(), "lastName");
    }




}
