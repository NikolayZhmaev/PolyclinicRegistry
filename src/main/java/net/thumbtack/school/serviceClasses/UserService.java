package net.thumbtack.school.serviceClasses;

import net.thumbtack.school.dao.UserDao;
import net.thumbtack.school.errors.ServiceErrorCode;
import net.thumbtack.school.errors.ServiceException;
import net.thumbtack.school.models.User;

import java.util.List;

public class UserService {
    private int max_name_length;
    private int min_password_length;
    private UserDao userDao;

    public UserService(int max_name_length, int min_password_length, UserDao userDao) {
        this.userDao = userDao;
        this.max_name_length = max_name_length;
        this.min_password_length = min_password_length;
    }

    // method for searching for a user in the database by login. If there is no such user in the database, an error occurs.
    public User userFindByLogin(String login) throws ServiceException {
        User userFind = userDao.getByLogin(login);
        if (userFind != null) {
            return userFind;
        } else throw new ServiceException(ServiceErrorCode.USER_NOT_FOUND);
    }

    // method for searching for a user in the database by id. If there is no such user in the database, an error occurs.
    public User userFindById(int id) throws ServiceException {
        User userFind = userDao.getById(id);
        if (userFind != null) {
            return userFind;
        } else throw new ServiceException(ServiceErrorCode.USER_NOT_FOUND);
    }

    // method for registering a user in the database
    public User userRegister(User user) throws ServiceException {
        fullVerificationUser(user);
        // after performing all the necessary checks, we register a new user in the database and return the result
        return userDao.insert(user);
    }

    // method for changing user data
    public User userUpdate(User user) throws ServiceException {
        fullVerificationUser(user);
        return userDao.update(user);
    }

    // method for getting all users from the database
    public List<User> usersGetAll() {
        return userDao.getAll();
    }

    // method for adding multiple users to the database
    public void usersBatchInsert(List<User> users) {
        userDao.batchInsert(users);
    }

    // method for deleting a user from the database
    public void userDelete(User user) {
        userDao.delete(user);
    }

    // method for deleting all users from the database
    public void usersDeleteAll() {
        userDao.deleteAll();
    }

    //let's put the full user check in a separate method
    public void fullVerificationUser(User user) throws ServiceException {
        checkFirstName(user.getFirstName());
        checkLastName(user.getLastName());
        if (user.getPatronymic() != null) {
            checkPatronymic(user.getPatronymic());
        }
        checkLogin(user.getLogin(), user.getId());
        checkPassword(user.getPassword());
    }

    /* must pass correct data during registration:
       - can only contain Russian letters
       - may contain spaces
       - may contain dashes
       - the maximum number of characters is set by the variable max_name_length
   */
    private void checkFirstName(String name) throws ServiceException {
        if (!name.matches("[А-Яа-я\\s\\-]{1," + max_name_length + "}")) {
            throw new ServiceException(ServiceErrorCode.USER_WRONG_FIRSTNAME);
        }
    }

    //similar to the previous method
    private void checkLastName(String name) throws ServiceException {
        try {
            checkFirstName(name);
        } catch (ServiceException e) {
            throw new ServiceException(ServiceErrorCode.USER_WRONG_LASTNAME);
        }
    }

    //similar to the previous method
    private void checkPatronymic(String name) throws ServiceException {
        try {
            checkFirstName(name);
        } catch (ServiceException e) {
            throw new ServiceException(ServiceErrorCode.USER_WRONG_PATRONYMIC);
        }
    }

    /* must pass correct data during registration. The login must contain:
       - it can only contain Latin and Russian letters
       - it can contain numbers
       - can't be empty
       - the maximum number of characters is set by the variable max_name_length
       - must be unique!!!
    */
    private void checkLogin(String login, int id) throws ServiceException {
        if (!login.matches("[a-zA-Zа-яА-Я0-9]{1," + max_name_length + "}")) {
            throw new ServiceException(ServiceErrorCode.USER_WRONG_LOGIN);
        }
        User user = userDao.getByLogin(login);
        if (user != null && user.getId() != id) {
            throw new ServiceException(ServiceErrorCode.THE_LOGIN_WAS);
        }
    }

    /* must pass correct data during registration. The password must contain:
       - can contain any characters
       - can't be empty
       - the minimum number of characters is set by the variable min_password_length
       - the maximum number of characters is set by the variable max_name_length
    */
    public void checkPassword(String password) throws ServiceException {
        if (!password.matches("[\\S\\s]{" + min_password_length + "," + max_name_length + "}")) {
            throw new ServiceException(ServiceErrorCode.USER_WRONG_PASSWORD);
        }
    }

    public int getMax_name_length() {
        return max_name_length;
    }

    public void setMax_name_length(int max_name_length) {
        this.max_name_length = max_name_length;
    }

    public int getMin_password_length() {
        return min_password_length;
    }

    public void setMin_password_length(int min_password_length) {
        this.min_password_length = min_password_length;
    }
}
