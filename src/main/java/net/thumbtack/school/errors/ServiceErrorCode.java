package net.thumbtack.school.errors;

public enum ServiceErrorCode {

    USER_WRONG_FIRSTNAME("First name is not valid"), // error when passing an invalid name
    USER_WRONG_LASTNAME("Last name is not valid"), // error when passing the wrong last name
    USER_WRONG_PATRONYMIC("Patronymic is not valid"), // error when passing the wrong patronymic
    USER_WRONG_LOGIN("Login is not valid"), // error when transmitting an invalid username
    THE_LOGIN_WAS("The login was"), // error registering a busy login
    USER_WRONG_PASSWORD("Password is not valid"), // error when transmitting an invalid password
    USER_NOT_FOUND("User not found"), // error if the user is not found in the database

    ;


    private String errorString;

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    ServiceErrorCode(String errorString) {
        this.errorString = errorString;
    }
}
