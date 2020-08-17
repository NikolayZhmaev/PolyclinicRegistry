package net.thumbtack.school.serviceClasses;

import com.google.gson.Gson;
import net.thumbtack.school.dao.AdministratorDao;
import net.thumbtack.school.dto.request.RegisterAdministratorDtoRequest;
import net.thumbtack.school.dto.response.ErrorResponse;
import net.thumbtack.school.dto.response.RegisterAdministratorDtoResponse;
import net.thumbtack.school.errors.ServiceException;
import net.thumbtack.school.models.Administrator;
import net.thumbtack.school.models.User;

public class AdministratorService {


    private Gson gson = new Gson();
    UserService userService;
    UserCategory userCategory = UserCategory.ADMINISTRATOR;
    AdministratorDao administratorDao;

    public AdministratorService(UserService userService, AdministratorDao administratorDao) {
        this.userService = userService;
        this.administratorDao = administratorDao;
    }

    public String administratorRegister(String requestJsonString) {
        RegisterAdministratorDtoRequest dtoRequest = gson.fromJson(requestJsonString, RegisterAdministratorDtoRequest.class);
        User user = new User(userCategory, dtoRequest.getFirstName(), dtoRequest.getLastName(), dtoRequest.getPatronymic(), dtoRequest.getLogin(), dtoRequest.getPassword());
        try {
            userService.fullVerificationUser(user);
            Administrator administrator = new Administrator(dtoRequest.getPosition(), user);
            administratorDao.insert(administrator);
            RegisterAdministratorDtoResponse regAdmResp = new RegisterAdministratorDtoResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getPatronymic(), administrator.getPosition());
            return gson.toJson(regAdmResp);
        } catch (ServiceException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), checkingMethod(e.getMessage()), e.getMessage());
            return gson.toJson(errorResponse);
        }
    }

    public String administratorLogout () {
        return null;

    }

    public String administratorLogin () {
        return null;

    }



    public String checkingMethod (String error) {
        switch (error) {
            case "First name is not valid" : return "firstName";
            case "Last name is not valid" : return "lastName";
            case "Patronymic is not valid" : return "patronymic";
            case "Login is not valid" : return "login";
            case "The login was" : return "login";
            case "Password is not valid" : return "password";
        }
        return null;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
